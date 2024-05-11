package com.sejong.bucketmanager.domain.reserve.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class LockerDetailCancelRequest {
    @Schema(description = "취소하는 학생의 PK (userId)")
    private Long userId;
}
