package com.sejong.bucketmanager.domain.locker.controller;

import com.sejong.bucketmanager.domain.locker.controller.response.LockersInfoInMajorResponse;
import com.sejong.bucketmanager.domain.locker.service.LockerService;
import com.sejong.bucketmanager.domain.locker.service.request.FindAllLockerInMajorRequestDto;
import com.sejong.bucketmanager.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.user.prefix}")
class LockerController {

    private final LockerService lockerUseCase;

    @Operation(
            summary = "사물함 정보조회",
            description = "사물함 이름, 기간, 각 사물함 칸의 예약여부정보"
    )
    @GetMapping("/users/{userId}/majors/lockers")
    public SuccessResponse<LockersInfoInMajorResponse> findAllLockerInMajor(@PathVariable("userId") Long userId) {
        long startTime = System.currentTimeMillis();
        SuccessResponse successResponse = new SuccessResponse(lockerUseCase.findAllLockerInMajor(FindAllLockerInMajorRequestDto.builder()
                .userId(userId).build()));
        long stopTime = System.currentTimeMillis();
        System.out.println("코드 실행 시간: " + (stopTime - startTime));
        return successResponse;

    }
}
