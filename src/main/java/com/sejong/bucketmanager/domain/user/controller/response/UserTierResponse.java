package com.sejong.bucketmanager.domain.user.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserTierResponse {
    @Schema(description = "학생의 변경된 납부여부")
    private Boolean isApprove;

}
