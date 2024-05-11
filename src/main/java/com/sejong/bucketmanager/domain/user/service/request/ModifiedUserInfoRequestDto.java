package com.sejong.bucketmanager.domain.user.service.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ModifiedUserInfoRequestDto {
    private List<ModifiedUserInfoDto> modifiedUserInfoList;

}
