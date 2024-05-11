package com.sejong.bucketmanager.domain.reserve.controller;

import com.sejong.bucketmanager.domain.locker.controller.response.LockerRegisterResponse;
import com.sejong.bucketmanager.domain.locker.service.request.LockerRegisterRequestDto;
import com.sejong.bucketmanager.domain.reserve.controller.request.LockerDetailCancelRequest;
import com.sejong.bucketmanager.domain.reserve.service.ReservationService;
import com.sejong.bucketmanager.domain.reserve.service.req.ChangeReservationRequestDto;
import com.sejong.bucketmanager.domain.user.service.request.UserCancelLockerRequestDto;
import com.sejong.bucketmanager.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.user.prefix}")
public class ReserveController {
    private final ReservationService reservationService;

    @Operation(
            summary = "사물함 취소",
            description = "현재 사용자의 예약된 사물함을 취소하는 API"
    )
    @PatchMapping("/lockerDetail/{lockerDetailId}/reservations")
    public SuccessResponse cancelLocker(@PathVariable("lockerDetailId") Long lockerDetailId,
                                        @Valid @RequestBody LockerDetailCancelRequest request) {
        reservationService.cancelReserve(
                UserCancelLockerRequestDto.of(request.getUserId(), lockerDetailId)
        );
        return SuccessResponse.ok();
    }

    //사물함 예약하는 api
    @Operation(
            summary = "사물함 예약",
            description = "사용자가 사물함을 선택할시 해당 사물함을 예약하는 API"
    )
    @PostMapping("/users/{userId}/majors/{majorId}/lockerDetail/{lockerDetailId}/reservations")
    public SuccessResponse<LockerRegisterResponse> registerLocker(
            @PathVariable("userId") Long userId,
            @PathVariable("lockerDetailId") Long lockerDetailId,
            @PathVariable("majorId") Long majorId) throws Exception {
//        log.info("{} : 시믈함 예약진행", principal.getName());
        LockerRegisterResponse lockerRegisterResponse = LockerRegisterResponse.fromResponse(
                reservationService.reserveForUser(LockerRegisterRequestDto.of(majorId, userId, lockerDetailId))
        );
        log.info("{} : {}의 {}번 예약완료",
                lockerRegisterResponse.getStudentNum(),
                lockerRegisterResponse.getLockerName(),
                lockerRegisterResponse.getLockerDetailNum());
        return new SuccessResponse(lockerRegisterResponse);
    }

    @Operation(
            summary = "사물함 예약변경",
            description = "사용자가 다른 사물함으로 변경을 희망할때 사용하는 API"
    )
    @PatchMapping("/users/{userId}/majors/{majorId}/lockerDetail/{originLockerDtailId}/reservations/change")
    public SuccessResponse changeReservation(@PathVariable("userId") Long userId,
                                             @PathVariable("originLockerDtailId") Long originLockerDtailId,
                                             @PathVariable("majorId") Long majorId,
                                             @RequestParam("newLockerDetailId") Long newLockerDetailId) {
        reservationService.changeReservation(
                ChangeReservationRequestDto.of(newLockerDetailId, originLockerDtailId, userId, majorId)
        );
        return SuccessResponse.ok();
    }
}
