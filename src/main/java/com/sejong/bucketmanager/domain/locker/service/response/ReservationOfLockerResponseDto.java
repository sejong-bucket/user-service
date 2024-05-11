package com.sejong.bucketmanager.domain.locker.service.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReservationOfLockerResponseDto {
    private List<Long> lockerIdList;
}
