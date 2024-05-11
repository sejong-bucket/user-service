package com.sejong.bucketmanager.domain.auth.service;

import com.sejong.bucketmanager.domain.auth.service.reponse.LoginTokenResponseDto;
import com.sejong.bucketmanager.domain.auth.service.reponse.ReissueTokenResponseDto;
import com.sejong.bucketmanager.domain.auth.service.request.LoginRequestDto;
import com.sejong.bucketmanager.domain.common.impl.SejongClient;
import com.sejong.bucketmanager.domain.common.repository.RedisRepository;
import com.sejong.bucketmanager.domain.major.entity.Major;
import com.sejong.bucketmanager.domain.major.entity.MajorDetail;
import com.sejong.bucketmanager.domain.major.impl.MajorDetailReader;
import com.sejong.bucketmanager.domain.user.entity.User;
import com.sejong.bucketmanager.domain.user.impl.UserReader;
import com.sejong.bucketmanager.domain.user.impl.UserWriter;
import com.sejong.bucketmanager.domain.user.repository.UserJpaRepository;
import com.sejong.bucketmanager.global.format.exception.auth.jwt.InvalidRefreshTokenException;
import com.sejong.bucketmanager.global.format.exception.user.NotFoundUserException;
import com.sejong.bucketmanager.global.util.HeaderUtil;
import com.sejong.bucketmanager.global.util.jwt.TokenProvider;
import com.sejong.bucketmanager.global.util.jwt.TokenSet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Objects;


@Transactional
@RequiredArgsConstructor
@Service
public class AuthService {
    //    private static final String USER_KEY = "USER_";
    private final TokenProvider tokenProvider;
    private final UserJpaRepository userJpaRepository;
    private final RedisRepository redisRepository;
    private final UserWriter userWriter;
    private final UserReader userReader;

    //    @DistributeLock(identifier = USER_KEY, key = "#dto.id")
    public LoginTokenResponseDto login(LoginRequestDto dto) {
        long now = System.currentTimeMillis();
        User user = userWriter.findOrUpdate(dto.toLoadSejongInfoDto());

        MajorDetail majorDetail = user.getMajorDetail();
        Major major = majorDetail.getMajor();

        TokenSet tokenSet = tokenProvider.createTokenSet(dto.getId());

        System.out.println("response time : " + (System.currentTimeMillis() - now));

        return LoginTokenResponseDto.of(
                tokenSet.getAccessToken(),
                tokenSet.getRefreshToken(),
                user.getRole(),
                user.getId(),
                major.getId(),
                major.getName()
        );
    }

    public ReissueTokenResponseDto reissue(String refreshToken) {
        String bearerToken = HeaderUtil.parseBearer(refreshToken);
        String studentNum = (String) tokenProvider.convertAuthToken(bearerToken).getTokenClaims().get("studentNum");
        String redisRT = redisRepository.getRefreshToken(studentNum);

        if (Objects.isNull(redisRT) || !redisRT.equals(refreshToken)) {
            throw new InvalidRefreshTokenException();
        }

        /*User byStudentNum = userReader.findByStudentNum(studentNum)
                .orElseThrow(NotFoundUserException::new);
                굳이 조회할 필요가 있을까?
        */

        TokenSet tokenSet = tokenProvider.createTokenSet(studentNum);

        redisRepository.removeAndSaveRefreshToken(studentNum, HeaderUtil.parseBearer(tokenSet.getRefreshToken()));

        return ReissueTokenResponseDto.of(tokenSet.getAccessToken(), tokenSet.getRefreshToken());
    }

    public void logout(String accessToken) {
        String bearerToken = HeaderUtil.parseBearer(accessToken);

        Long tokenExpiration = tokenProvider.getTokenExpiration(bearerToken);

        String studentNum = SecurityContextHolder.getContext().getAuthentication().getName();

        if (redisRepository.getRefreshToken(studentNum) != null) {
            redisRepository.removeRefreshToken(studentNum);
        }

        redisRepository.logoutTokenSave(HeaderUtil.parseBearer(accessToken), Duration.ofMillis(tokenExpiration));
    }
}