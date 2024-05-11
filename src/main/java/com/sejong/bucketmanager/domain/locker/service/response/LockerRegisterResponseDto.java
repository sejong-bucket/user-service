package com.sejong.bucketmanager.domain.locker.service.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LockerRegisterResponseDto {
    private String lockerDetailNum;
    private String studentNum;
    private String lockerName;

    public static LockerRegisterResponseDto of(String lockerDetailNum,String studentNum,String lockerName){
        return LockerRegisterResponseDto.builder()
                .lockerDetailNum(lockerDetailNum)
                .lockerName(lockerName)
                .studentNum(studentNum)
                .build();
    }
}
