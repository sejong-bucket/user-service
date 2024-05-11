package com.sejong.bucketmanager.domain.reserve.controller;

import com.sejong.bucketmanager.domain.reserve.service.ReservationService;
import com.sejong.bucketmanager.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.admin.prefix}")
public class AdminReserveController {
    private final ReservationService reservationService;
    @Operation(
            summary = "사물함 예약 전체취소",
            description = "사물함 초기화(eg. 학기초 및 롤백)의 경우 예약된 모든 사물함을 초기화하는 API, " +
                    "사물함 데이터를 남기기위해 삭제하는것이 아닌 ENUM수정으로 취소 표현" +
                    "예약 마감날짜에 자동 초기화로 수정 예정 사용하지 말기"
    )
    @PostMapping("/locker/{lockerId}/reservations")
    public SuccessResponse resetLocker(@PathVariable("lockerId") Long lockerId){
        reservationService.resetReservation(lockerId);
        return SuccessResponse.ok("초기화가 완료되었습니다.");
    }
}
