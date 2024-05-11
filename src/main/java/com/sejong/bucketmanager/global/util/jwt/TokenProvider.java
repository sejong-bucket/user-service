package com.sejong.bucketmanager.global.util.jwt;

import com.sejong.bucketmanager.domain.auth.entity.AuthToken;
import com.sejong.bucketmanager.domain.common.repository.RedisRepository;
import com.sejong.bucketmanager.global.format.exception.auth.TokenValidFailedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {
    @Value("${jwt.access-token-expiration}")
    private long ACCESS_TOKEN_VALIDATiON_SECOND;
    //28일
    @Value("${jwt.refresh-token-expiration}")
    private long REFRESH_TOKEN_VALIDATiON_SECOND;
    private Key key;
    private final RedisRepository redisRepository;

    @Autowired
    public TokenProvider(@Value("${jwt.secret-key}") String secret,
                         RedisRepository redisRepository) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.redisRepository = redisRepository;
    }

    /**
     * todo
     * 테스트코드 짜기
     */
    public TokenSet createTokenSet(String studentNum) {
        String accessJwt = createJwt(studentNum, ACCESS_TOKEN_VALIDATiON_SECOND);
        String refreshJwt = createJwt(studentNum, REFRESH_TOKEN_VALIDATiON_SECOND);
        return TokenSet.ofBearer(accessJwt, refreshJwt);
    }

    private String createJwt(String studentNum, long duration) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + duration);
        return Jwts.builder()
                .setClaims(createClaimByStudentNum(studentNum))
                .setSubject(studentNum)
                .setExpiration(expiration)
                .setIssuedAt(now)
                .signWith(key)
                .compact();
    }

    private Map<String, Object> createClaimByStudentNum(String studentNum) {
        Map<String, Object> map = new HashMap<>();
        map.put("studentNum", studentNum);
        return map;
    }

    public AuthToken convertAuthToken(String token) {
        return AuthToken.of(token, key);
    }

    public Long getTokenExpiration(String token) {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return body.getExpiration().getTime();
    }

    public Authentication getAuthentication(AuthToken authToken) {
        if (authToken.validate()) {
            Claims tokenClaims = authToken.getTokenClaims();
            Collection<? extends GrantedAuthority> authorities = Arrays.stream(new String[]{tokenClaims
                            .get("studentNum")
                            .toString()})
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            User principal = new User((String) tokenClaims.get("studentNum"), "", authorities);
            return new UsernamePasswordAuthenticationToken(principal, null, authorities);
        } else {
            throw new TokenValidFailedException();
        }
    }
}