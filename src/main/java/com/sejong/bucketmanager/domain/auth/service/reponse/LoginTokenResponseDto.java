package com.sejong.bucketmanager.domain.auth.service.reponse;

import com.sejong.bucketmanager.domain.auth.controller.response.LoginTokenResponse;
import com.sejong.bucketmanager.domain.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginTokenResponseDto {
    private String accessToken;
    private String refreshToken;
    private Role role;
    private Long userId;

    private Long majorId;
    private String majorName;
    public static LoginTokenResponseDto of(String accessToken,
                                           String refreshToken,
                                           Role role,
                                           Long userId,
                                           Long majorId,
                                           String majorName
    ) {
        return new LoginTokenResponseDto().builder()
                .majorName(majorName)
                .majorId(majorId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .role(role)
                .userId(userId)
                .build();
    }

    public LoginTokenResponse toResponse() {
        return LoginTokenResponse.builder()
                .majorName(majorName)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .role(role)
                .userId(userId)
                .majorId(majorId)
                .build();
    }
}
