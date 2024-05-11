package com.sejong.bucketmanager.domain.user.service.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoRequestDto {
    private Long userId;
}
