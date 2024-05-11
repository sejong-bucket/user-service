package com.sejong.bucketmanager.domain.user.controller.request;

import com.sejong.bucketmanager.domain.user.service.request.ModifiedUserInfoRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ModifiedUserInfoRequest {

    @Valid
    @Size(min = 1)
    private List<ModifiedUserInfo> modifiedUserInfoList;

    public ModifiedUserInfoRequestDto toRequestDto() {
        return ModifiedUserInfoRequestDto.builder()
                .modifiedUserInfoList(modifiedUserInfoList.stream()
                        .map(ModifiedUserInfo::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
