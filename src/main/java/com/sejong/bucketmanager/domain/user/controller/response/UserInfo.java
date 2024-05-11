package com.sejong.bucketmanager.domain.user.controller.response;

import com.sejong.bucketmanager.domain.user.entity.Role;
import com.sejong.bucketmanager.domain.user.entity.UserTier;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "예약된 학생의 정보")
@Getter
@Builder
public class UserInfo {
    @Schema(description = "학생이름")
    private String studentName;
    @Schema(description = "학생의 학번")
    private String studentNum;
    @Schema(description = "학생회비 납부여부")
    private UserTier userTier;
    @Schema(description = "학생의 pk값")
    private Long userId;
    @Schema(description = "관리자 여부")
    private Role role;
    @Schema(description = "학생의 재학 상태")
    private String status;
}
