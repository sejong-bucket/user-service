package com.sejong.bucketmanager.domain.locker.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sejong.bucketmanager.domain.user.entity.UserTier;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "사물함 생성시 요청 DTO")
@Getter
public class LockerCreateRequest {
    @Schema(description = "생성할 사물함 이름")
    @NotBlank(message = "사물함 이름을 설정해주세요.")
    private String lockerName;
    @Schema(description = "생성할 사물함의 전체 행 개수")
    @NotBlank(message = "사물함의 전체 행의 개수를 설정해주세요.")
    private String totalRow;
    @Schema(description = "생성할 사물함의 전체 열 개수")
    @NotBlank(message = "사물함의 전체 열의 개수를 설정해주세요.")
    private String totalColumn;
    @Schema(description = "생성할 사물함의 예약 시작 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "사물함 예약 시작 시간을 설정해주세요.")
    private LocalDateTime startReservationTime;
    @Schema(description = "생성할 사물함의 예약 마감 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "사물함 예약 마감 시간을 설정해주세요.")
    private LocalDateTime endReservationTime;
    /*@Schema(description = "생성할 사물함 재학 여부에 따른 허용조건")
    @NotNull(message = "사용자 재학상태에 따른 사물함 이용제약을 설정해주세요.")
    private List<UserState> userStates;*/
    @Schema(description = "생성할 사물함 학생회비 납부 여부에 따른 허용조건")
    @NotNull(message = "학생회비 납부에 따른 사물함 이용제약을 설정해주세요.")
    private List<UserTier> userTiers;

    /*@Schema(description = "생성할 사물함의 각 칸 정보")
    private List<LockerDetailCreateRequest> lockerDetailCreateRequests;*/

    @Schema(description = "사물함 번호 증가방향")
    @NotNull(message = "사물함 번호의 증가방향을 설정해주세요")
    private NumberIncreaseDirection numberIncreaseDirection;
}
