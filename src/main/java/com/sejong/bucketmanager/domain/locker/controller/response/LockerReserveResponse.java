package com.sejong.bucketmanager.domain.locker.controller.response;

import com.sejong.bucketmanager.domain.locker.service.response.ReservationOfLockerResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LockerReserveResponse {
    List<Long> reservedLockerId;
    public static LockerReserveResponse fromResponse(ReservationOfLockerResponseDto reserveLocker) {
        return LockerReserveResponse.builder()
                .reservedLockerId(reserveLocker.getLockerIdList())
                .build();
    }
}
