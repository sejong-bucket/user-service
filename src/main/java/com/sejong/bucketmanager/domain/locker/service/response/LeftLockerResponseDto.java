package com.sejong.bucketmanager.domain.locker.service.response;

import com.sejong.bucketmanager.domain.locker.service.dto.CreatedLockerInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class LeftLockerResponseDto {

    private List<CreatedLockerInfo> createdLockerInfo;
    public CreatedLockersInfoResponse toResponse(){
        return CreatedLockersInfoResponse.builder()
                .createdLockerInfo(createdLockerInfo)
                .build();
    }

}
