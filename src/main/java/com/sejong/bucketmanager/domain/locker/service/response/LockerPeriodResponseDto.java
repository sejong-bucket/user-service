package com.sejong.bucketmanager.domain.locker.service.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LockerPeriodResponseDto {
    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    @Builder
    public LockerPeriodResponseDto(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
