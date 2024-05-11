package com.sejong.bucketmanager.domain.locker.entity.dto;


import com.sejong.bucketmanager.domain.locker.entity.Locker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LockerDetailCreateDto {
    private Locker locker;
    private String lockerNum;
    private String rowNum;

    private String columnNum;
}
