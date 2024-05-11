package com.sejong.bucketmanager.domain.locker.service.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sejong.bucketmanager.domain.locker.entity.dto.LockerDetailInfo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class AllLockersInMajorResponseDto {
    private String lockerName;

    @JsonFormat(pattern = "YYYY-MM-dd hh:mm:ss")
    private LocalDateTime startReservationTime;
    @JsonFormat(pattern = "YYYY-MM-dd hh:mm:ss")
    private LocalDateTime endReservationTime;
    private List<LockerDetailInfo> lockerDetailInfoList;
}
