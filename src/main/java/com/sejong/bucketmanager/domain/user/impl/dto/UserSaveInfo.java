package com.sejong.bucketmanager.domain.user.impl.dto;

import com.sejong.bucketmanager.domain.major.entity.MajorDetail;
import com.sejong.bucketmanager.domain.user.entity.UserState;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserSaveInfo {
    private String grade;
    private MajorDetail majorDetail;
    private String name;
    private String userState;
    private String studentNum;
}
