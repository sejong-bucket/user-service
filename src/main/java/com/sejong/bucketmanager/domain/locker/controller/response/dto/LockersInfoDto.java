package com.sejong.bucketmanager.domain.locker.controller.response.dto;

import com.sejong.bucketmanager.domain.locker.entity.dto.LockerDetailInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LockersInfoDto {
    @Schema(description = "사물함에 대한 정보")
    private LockersInfoInMajorDto locker;
    @Schema(description = "사물함 칸에 대한 정보")
    private List<LockerDetailInfo> lockerDetail;
}
