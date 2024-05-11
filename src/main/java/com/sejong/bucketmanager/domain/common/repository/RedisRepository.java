package com.sejong.bucketmanager.domain.common.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Repository
public class RedisRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    public String getRefreshToken(String authId) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return "Bearer " + valueOperations.get(authId);
    }

    public void removeRefreshToken(String authId) {
        redisTemplate.delete(authId);
    }

    public String getValue(String token) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String value = (String) valueOperations.get(token);
        return value;
    }

    public void logoutTokenSave(String accessToken, Duration leftTime) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(accessToken, "logout", leftTime);
    }

    public void removeAndSaveRefreshToken(String studentNum, String refreshToken) {
        redisTemplate.delete(studentNum);
        refreshSave(studentNum, refreshToken);
    }

    public void refreshSave(String studentNum, String refreshToken) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(studentNum, refreshToken, 14, TimeUnit.DAYS);
    }
}
