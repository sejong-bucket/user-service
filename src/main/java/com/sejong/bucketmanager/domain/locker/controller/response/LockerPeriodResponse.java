package com.sejong.bucketmanager.domain.locker.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sejong.bucketmanager.domain.locker.service.response.LockerPeriodResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class LockerPeriodResponse {
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDateTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDateTime;

    public static LockerPeriodResponse fromResponse(LockerPeriodResponseDto responseDto){
        return LockerPeriodResponse.builder()
                .startDateTime(responseDto.getStartDateTime())
                .endDateTime(responseDto.getEndDateTime())
                .build();
    }
}
