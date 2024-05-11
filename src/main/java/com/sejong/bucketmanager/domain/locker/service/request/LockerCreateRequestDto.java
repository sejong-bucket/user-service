package com.sejong.bucketmanager.domain.locker.service.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sejong.bucketmanager.domain.locker.controller.request.LockerCreateRequest;
import com.sejong.bucketmanager.domain.locker.controller.request.NumberIncreaseDirection;
import com.sejong.bucketmanager.domain.locker.entity.dto.LockerCreateDto;
import com.sejong.bucketmanager.domain.major.entity.Major;
import com.sejong.bucketmanager.domain.user.entity.UserState;
import com.sejong.bucketmanager.domain.user.entity.UserTier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LockerCreateRequestDto {
    private String lockerName;
    private String totalRow;
    private String totalColumn;
    /*private MultipartFile image;*/
    private List<UserState> userStates;
    private List<UserTier> userTiers;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startReservationTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endReservationTime;
    //    private List<LockerDetailCreateRequest> lockerDetailCreateRequests;
    private NumberIncreaseDirection numberIncreaseDirection;
    public static LockerCreateRequestDto fromRequest(LockerCreateRequest lockerCreateRequest/*,MultipartFile image*/) {
        return LockerCreateRequestDto.builder()
                .lockerName(lockerCreateRequest.getLockerName())
                .totalRow(lockerCreateRequest.getTotalRow())
                .totalColumn(lockerCreateRequest.getTotalColumn())
                .startReservationTime(lockerCreateRequest.getStartReservationTime())
                .endReservationTime(lockerCreateRequest.getEndReservationTime())
//                .image(image)
                .userTiers(lockerCreateRequest.getUserTiers())
                .numberIncreaseDirection(lockerCreateRequest.getNumberIncreaseDirection())
                .userStates(List.of(UserState.ATTEND))
                .build();
    }

    public LockerCreateDto toLockerCreateDto(Major major/*,String imageUrl*/) {
        return LockerCreateDto.builder()
                .totalRow(this.getTotalRow())
                .totalColumn(this.getTotalColumn())
                .lockerName(this.getLockerName())
                .userStates(this.userStates)
                .userTiers(this.userTiers)
                .startReservationTime(this.getStartReservationTime())
                .endReservationTime(this.getEndReservationTime())
                .major(major)
//                .imageUrl(imageUrl)
                .build();
    }
}
