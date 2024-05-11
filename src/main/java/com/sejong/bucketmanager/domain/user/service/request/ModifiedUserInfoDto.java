package com.sejong.bucketmanager.domain.user.service.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ModifiedUserInfoDto {
    private String studentNum;
    private Boolean admin;
    private Boolean membership;
    private Long lockerDetailId;
}
