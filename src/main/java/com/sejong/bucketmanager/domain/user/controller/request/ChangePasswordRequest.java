package com.sejong.bucketmanager.domain.user.controller.request;

import lombok.Getter;

@Getter
public class ChangePasswordRequest {
    private String newPassword;
    private String currentPassword;
}