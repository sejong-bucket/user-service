package com.sejong.bucketmanager.domain.locker.service.dto;

import com.sejong.bucketmanager.domain.locker.controller.request.NumberIncreaseDirection;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateLockerDetailDto {
    private Integer totalRow;
    private Integer totalColumn;
    private NumberIncreaseDirection numberIncreaseDirection;
}
