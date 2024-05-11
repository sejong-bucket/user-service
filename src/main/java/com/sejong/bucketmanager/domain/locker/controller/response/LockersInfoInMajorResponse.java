package com.sejong.bucketmanager.domain.locker.controller.response;

import com.sejong.bucketmanager.domain.locker.controller.response.dto.LockersInfoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LockersInfoInMajorResponse {
    @Schema(description = "사물함과 칸들의 정보")
    private List<LockersInfoDto> lockersInfo;
}
