package com.sejong.bucketmanager.domain.auth.service.dto;

import com.sejong.bucketmanager.domain.major.entity.MajorDetail;
import com.sejong.bucketmanager.domain.user.entity.UserState;
import com.sejong.bucketmanager.domain.user.entity.UserTier;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class UpdateUserInfoDto {
    private UserState status;
    private String grade;
    private MajorDetail majorDetail;
    private boolean auth;
}
