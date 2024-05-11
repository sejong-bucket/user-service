package com.sejong.bucketmanager.domain.user.service.response;

import com.sejong.bucketmanager.domain.user.controller.response.UserTierResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserTierResponseDto {
    private Boolean isApprove;

    public UserTierResponse toResponse() {
        return UserTierResponse.builder()
                .isApprove(isApprove)
                .build();
    }

}
