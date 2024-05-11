package com.sejong.bucketmanager.domain.auth.service.request;

import com.sejong.bucketmanager.domain.common.impl.request.LoadSejongInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    private String id;
    private String pw;

    public LoadSejongInfoDto toLoadSejongInfoDto() {
        return LoadSejongInfoDto.builder()
                .id(id)
                .pw(pw)
                .build();
    }

}
