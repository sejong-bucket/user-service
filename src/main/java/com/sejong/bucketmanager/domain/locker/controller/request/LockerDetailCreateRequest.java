package com.sejong.bucketmanager.domain.locker.controller.request;

import com.sejong.bucketmanager.domain.locker.entity.Locker;
import com.sejong.bucketmanager.domain.locker.entity.dto.LockerDetailCreateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Schema(description = "생성할 사물함의 각 칸 정보")
@Getter
public class LockerDetailCreateRequest {
    @Schema(description = "사물함 칸의 번호")
    @NotBlank
    private String lockerNum;
    @Schema(description = "사물함 칸의 행번호")
    @NotBlank
    private String rowNum;
    @Schema(description = "사물함 칸의 열번호")
    @NotBlank
    private String columnNum;
    /*@Schema(description = "사물함 칸의 현재 상태",allowableValues = {"RESERVED","NON_RESERVED","BROKEN"})
    private LockerDetailStatus lockerDetailStatus;*/

    public LockerDetailCreateDto toCreateDto(Locker locker){
        return LockerDetailCreateDto.builder()
                .locker(locker)
                .lockerNum(this.lockerNum)
                .columnNum(this.columnNum)
                .rowNum(this.rowNum)
//                .lockerDetailStatus(this.lockerDetailStatus)
                .build();
    }

}
