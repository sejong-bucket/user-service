package com.sejong.bucketmanager.domain.auth.controller.request;

import com.sejong.bucketmanager.domain.auth.service.request.LoginRequestDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class LoginRequest {
    @NotNull
    private String id;
    @NotNull
    private String pw;

    public LoginRequestDto toRequestDto(){
        return LoginRequestDto.builder()
                .id(id)
                .pw(pw)
                .build();
    }

}
