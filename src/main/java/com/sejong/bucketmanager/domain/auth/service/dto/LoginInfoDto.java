package com.sejong.bucketmanager.domain.auth.service.dto;

import com.sejong.bucketmanager.domain.major.entity.MajorDetail;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginInfoDto {
    private String name;
    private String status;
    private String studentNum;
    private String grade;
    private MajorDetail majorDetail;
}
