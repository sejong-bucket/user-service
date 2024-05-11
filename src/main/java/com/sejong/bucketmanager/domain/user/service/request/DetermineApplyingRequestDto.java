package com.sejong.bucketmanager.domain.user.service.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DetermineApplyingRequestDto {
    private String studentNum;
}
