package com.sejong.bucketmanager.domain.user.impl;

import com.sejong.bucketmanager.domain.auth.service.dto.UpdateUserInfoDto;
import com.sejong.bucketmanager.domain.common.impl.SejongClient;
import com.sejong.bucketmanager.domain.common.impl.request.LoadSejongInfoDto;
import com.sejong.bucketmanager.domain.common.impl.response.SejongMemberResponseDto;
import com.sejong.bucketmanager.domain.major.entity.MajorDetail;
import com.sejong.bucketmanager.domain.major.impl.MajorDetailReader;
import com.sejong.bucketmanager.domain.user.entity.User;
import com.sejong.bucketmanager.domain.user.entity.UserState;
import com.sejong.bucketmanager.domain.user.impl.dto.UserSaveInfo;
import com.sejong.bucketmanager.domain.user.repository.UserJpaRepository;
import com.sejong.bucketmanager.global.aop.meta.ImplService;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.sejong.bucketmanager.domain.user.entity.Role.ROLE_USER;
import static com.sejong.bucketmanager.domain.user.entity.UserTier.MEMBER;

@RequiredArgsConstructor
@ImplService
public class UserWriter {
    private final UserJpaRepository userJpaRepository;
    private final SejongClient sejongClient;
    private final UserReader userReader;
    private final MajorDetailReader majorDetailReader;

    public User findOrUpdate(LoadSejongInfoDto dto) {
        User user = userReader.readByStudentNumWithMajorDetailAndMajor(dto.getId());

        if (user == null || isOverSemester(user)) {
            SejongMemberResponseDto sejongInfo = sejongClient.callSejongLoginApi(dto);
            MajorDetail majorDetail = majorDetailReader.readByName(getMajorDetailName(sejongInfo));

            if (user == null) {
                user = save(createUser(UserSaveInfo.builder()
                        .grade(sejongInfo.getResult().getBody().getGrade())
                        .userState(sejongInfo.getResult().getBody().getStatus())
                        .majorDetail(majorDetail)
                        .name(sejongInfo.getResult().getBody().getName())
                        .studentNum(dto.getId())
                        .build()));
            } else {
                user = updateUserInfo(sejongInfo, majorDetail, user);
            }
        }
        return user;
    }

    private static boolean isOverSemester(User user) {
        return Duration.between(user.getModifiedTime(), LocalDateTime.now()).toDays() / 30 >= 4;
    }

    private User save(User user) {
        return userJpaRepository.save(user);
    }

    private User updateUserInfo(SejongMemberResponseDto sejongMemberResponseDto, MajorDetail majorDetail, User user) {
        UserState matchUserState = UserState.match(sejongMemberResponseDto.getResult().getBody().getStatus());
        return user.updateUserInfo(UpdateUserInfoDto.builder()
                .auth(true)
                .status(matchUserState)
                .grade(sejongMemberResponseDto.getResult().getBody().getGrade())
                .majorDetail(majorDetail)
                .build());
    }

    private String getMajorDetailName(SejongMemberResponseDto sejongMemberResponseDto) {
        return sejongMemberResponseDto.getResult().getBody().getMajor();
    }

    private User createUser(UserSaveInfo loginInfoDto) {
        return User.builder()
                .name(loginInfoDto.getName())
                .userState(UserState.match(loginInfoDto.getUserState()))
                .studentNum(loginInfoDto.getStudentNum())
                .role(ROLE_USER)
                .auth(true)
                .grade(loginInfoDto.getGrade())
                .majorDetail(loginInfoDto.getMajorDetail())
                .build();
    }
}
