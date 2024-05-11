package com.sejong.bucketmanager.domain.locker.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sejong.bucketmanager.domain.major.entity.Major;
import com.sejong.bucketmanager.domain.user.entity.UserState;
import com.sejong.bucketmanager.domain.user.entity.UserTier;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class LockerCreateDto {
    private Major major;
    private String lockerName;
    private String totalRow;
    private String totalColumn;
    private String imageUrl;
    private List<UserState> userStates;
    private List<UserTier> userTiers;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startReservationTime;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endReservationTime;
}
