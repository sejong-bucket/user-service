package com.sejong.bucketmanager.domain.user.service.response;

import com.sejong.bucketmanager.domain.user.controller.response.CheckMembershipResponse;
import com.sejong.bucketmanager.domain.user.entity.UserTier;
import lombok.Builder;

@Builder
public class CheckMembershipResponseDto {
    private UserTier userTier;

    public CheckMembershipResponse toResponse() {
        return CheckMembershipResponse.builder()
                .userTier(userTier)
                .build();
    }
}
