package com.sejong.bucketmanager.domain.locker.controller;

import com.sejong.bucketmanager.domain.locker.controller.request.LockerCreateRequest;
import com.sejong.bucketmanager.domain.locker.controller.request.ModifyLockerInfoReqeust;
import com.sejong.bucketmanager.domain.locker.controller.response.LockerCreateResponse;
import com.sejong.bucketmanager.domain.locker.service.LockerService;
import com.sejong.bucketmanager.domain.locker.service.request.LockerCreateRequestDto;
import com.sejong.bucketmanager.domain.locker.service.response.CreatedLockersInfoResponse;
import com.sejong.bucketmanager.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.admin.prefix}")
class LockerAdminController {
    private final LockerService lockerService;

    @Operation(
            summary = "[관리자페이지] 생성된 사물함 정보조회 api"
    )
    @GetMapping("/majors/{majorId}/lockers")
    public SuccessResponse<CreatedLockersInfoResponse> getCreatedLockers(@PathVariable("majorId") Long majorId) {
        return new SuccessResponse(lockerService.getCreatedLockers(majorId).toResponse());
    }

    @Operation(
            summary = "새로운 사물함 생성",
            description = "새로운 사물함을 생성하는 API"
    )
    @PostMapping("/majors/{majorId}/lockers")
    public SuccessResponse<LockerCreateResponse> createLocker(
            @PathVariable("majorId") Long majorId,
            @Valid @RequestBody LockerCreateRequest lockerCreateRequest) throws IOException {
        String createdLockerName = lockerService.createLocker(
                        LockerCreateRequestDto
                                .fromRequest(lockerCreateRequest/*, image*/), majorId
                )
                .getCreatedLockerName();
        return new SuccessResponse(
                LockerCreateResponse.builder()
                        .createdLockerName(createdLockerName)
                        .build()
        );
    }

    @Operation(
            summary = "사물함 정보 수정",
            description = "사물함의 여러 정보를 수정하는 api"
    )
    @PatchMapping("/lockers/{lockerId}")
    public SuccessResponse modifyLockerInfo(@PathVariable("lockerId") Long lockerId,
                                            @RequestPart(required = false) MultipartFile image,
                                            @Valid @RequestPart ModifyLockerInfoReqeust modifyLockerInfoReqeust) throws IOException {
        lockerService.modifyLockerInfo(modifyLockerInfoReqeust.toReqeustDto(lockerId, image));
        return SuccessResponse.ok();
    }
}
