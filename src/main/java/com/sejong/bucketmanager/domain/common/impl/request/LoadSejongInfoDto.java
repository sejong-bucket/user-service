package com.sejong.bucketmanager.domain.common.impl.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoadSejongInfoDto {
    private String id;
    private String pw;

    @Builder
    public LoadSejongInfoDto(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }
}
