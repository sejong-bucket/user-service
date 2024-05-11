package com.sejong.bucketmanager.domain.locker.service.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LockerCreateResponseDto {
    private String createdLockerName;
    private Long createdLockerId;
}
