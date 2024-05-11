package com.sejong.bucketmanager.domain.user.service.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCancelLockerRequestDto {
    private Long userId;
    private Long lockerDetailId;

    public static UserCancelLockerRequestDto of(Long userId,Long lockerDetailId){
        return UserCancelLockerRequestDto.builder()
                .lockerDetailId(lockerDetailId)
                .userId(userId)
                .build();
    }
}
