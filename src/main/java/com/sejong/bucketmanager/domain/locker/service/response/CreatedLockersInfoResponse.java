package com.sejong.bucketmanager.domain.locker.service.response;

import com.sejong.bucketmanager.domain.locker.service.dto.CreatedLockerInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Schema(description = "생성된 사물함 목록 응답DTO")
public class CreatedLockersInfoResponse {
    private List<CreatedLockerInfo> createdLockerInfo;

}
