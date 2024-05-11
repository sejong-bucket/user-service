package com.sejong.bucketmanager.domain.locker.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sejong.bucketmanager.domain.locker.service.request.ModifyLockerInfoReqeustDto;
import com.sejong.bucketmanager.domain.user.entity.UserState;
import com.sejong.bucketmanager.domain.user.entity.UserTier;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ModifyLockerInfoReqeust {
    private String lockerName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    private List<UserState> userStates;
    private List<UserTier> userTiers;

    public ModifyLockerInfoReqeustDto toReqeustDto(Long lockerId, MultipartFile image) {
        return ModifyLockerInfoReqeustDto.builder()
                .lockerName(lockerName)
                .lockerId(lockerId)
                .startTime(startTime)
                .endTime(endTime)
                .image(image)
                .userStates(userStates)
                .userTiers(userTiers)
                .build();
    }

}
