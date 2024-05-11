package com.sejong.bucketmanager.domain.locker.service.request;

import com.sejong.bucketmanager.domain.user.entity.UserState;
import com.sejong.bucketmanager.domain.user.entity.UserTier;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class ModifyLockerInfoReqeustDto {
    private String lockerName;
    private Long lockerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<UserState> userStates;
    private List<UserTier> userTiers;
    private MultipartFile image;
}
