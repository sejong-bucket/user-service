package com.sejong.bucketmanager.domain.auth.controller.response;

import com.sejong.bucketmanager.domain.user.entity.Role;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginTokenResponse {
    private Role role;
    private Long userId;
    private Long majorId;
    private String majorName;
    private String accessToken;
    private String refreshToken;
}
