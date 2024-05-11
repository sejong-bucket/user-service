package com.sejong.bucketmanager.domain.user.controller.request;

import com.sejong.bucketmanager.domain.user.service.request.DetermineApplyingRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class DetermineApplyingRequest {
    @NotBlank
    @Schema(description = "승인을 받을 학생의 학번")
    private String studentNum;

    public DetermineApplyingRequestDto toRequestDto() {
        return DetermineApplyingRequestDto.builder()
                .studentNum(studentNum).build();
    }
}
