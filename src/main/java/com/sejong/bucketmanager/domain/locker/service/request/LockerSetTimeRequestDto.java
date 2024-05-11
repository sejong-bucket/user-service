package com.sejong.bucketmanager.domain.locker.service.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class LockerSetTimeRequestDto {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
