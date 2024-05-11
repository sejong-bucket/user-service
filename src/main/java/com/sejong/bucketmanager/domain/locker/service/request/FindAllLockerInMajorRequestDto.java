package com.sejong.bucketmanager.domain.locker.service.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindAllLockerInMajorRequestDto {
    private Long userId;
}
