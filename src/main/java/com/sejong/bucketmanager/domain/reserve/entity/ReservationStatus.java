package com.sejong.bucketmanager.domain.reserve.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReservationStatus {
    RESERVED("예약"),CANCEL("사용자 취소"),RESET("초기화");
    private String krName;
}
