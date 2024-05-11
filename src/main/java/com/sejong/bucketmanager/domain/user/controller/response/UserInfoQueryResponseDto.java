package com.sejong.bucketmanager.domain.user.controller.response;

import com.sejong.bucketmanager.domain.user.entity.Role;
import com.sejong.bucketmanager.domain.user.entity.UserState;
import com.sejong.bucketmanager.domain.user.entity.UserTier;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserInfoQueryResponseDto {
    private String name;
    private UserTier userTier;
    private UserState userState;
    private String status;
    private String studentNum;

    private String lockerDetailNum;
    private String lockerName;
    private Long lockerDetailId;
    private String majorDetail;
    private Long userId;
    private Long majorId;
    private Role role;

    @Builder
    public UserInfoQueryResponseDto(String name,
                                    UserTier userTier,
                                    UserState userState,
                                    String status,
                                    String studentNum,
                                    String lockerDetailNum,
                                    String lockerName,
                                    Long lockerDetailId,
                                    String majorDetail,
                                    Long userId,
                                    Long majorId,
                                    Role role) {
        this.name = name;
        this.userTier = userTier;
        this.userState = userState;
        this.status = status;
        this.studentNum = studentNum;
        this.majorDetail = majorDetail;
        this.userId = userId;
        this.majorId = majorId;
        this.role = role;
        if (lockerDetailNum != null) {
            this.lockerDetailId = lockerDetailId;
            this.lockerDetailNum = lockerDetailNum;
            this.lockerName = lockerName;
        } else {
            this.lockerDetailId = null;
            this.lockerDetailNum = null;
            this.lockerName = null;
        }
    }
/*
    @Builder
    public UserInfoQueryResponseDto(Long lockerDetailId,
                                    String name,
                                    UserTier userTier,
                                    String status,
                                    String studentNum,
                                    String lockerDetailNum,
                                    String lockerName,
                                    String majorDetail,
                                    UserState userState) {
        this.userState = userState;
        this.name = name;
        this.userTier = userTier;
        this.status = status;
        this.studentNum = studentNum;
        this.majorDetail = majorDetail;
        if (lockerDetailNum != null) {
            this.lockerDetailId = lockerDetailId;
            this.lockerDetailNum = lockerDetailNum;
            this.lockerName = lockerName;
        } else {
            this.lockerDetailId = null;
            this.lockerDetailNum = null;
            this.lockerName = null;
        }
    }*/
}
