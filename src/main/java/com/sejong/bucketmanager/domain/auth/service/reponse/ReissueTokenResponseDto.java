package com.sejong.bucketmanager.domain.auth.service.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReissueTokenResponseDto {
    private String accessToken;
    private String refreshToken;

    public static ReissueTokenResponseDto of(String accessToken, String refreshToken){
        return new ReissueTokenResponseDto().builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
