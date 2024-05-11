package com.sejong.bucketmanager.domain.locker.controller.request;

import com.sejong.bucketmanager.domain.locker.service.request.LockerSetTimeRequestDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LockerSetTimeRequest {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public LockerSetTimeRequestDto toRequestDto(){
        return LockerSetTimeRequestDto.builder()
                .endDateTime(endDateTime)
                .startDateTime(startDateTime)
                .build();
    }
}
