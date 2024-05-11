package com.sejong.bucketmanager.domain.user.controller.response;

import com.sejong.bucketmanager.domain.user.entity.UserTier;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckMembershipResponse {
    private UserTier userTier;
}
