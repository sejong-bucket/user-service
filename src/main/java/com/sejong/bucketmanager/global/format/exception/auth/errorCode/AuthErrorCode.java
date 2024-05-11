package com.sejong.bucketmanager.global.format.exception.auth.errorCode;

import com.sejong.bucketmanager.global.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ErrorEnumCode {
    INVALID_LOGIN_PARAM("AE001","로그인 정보가 틀렸습니다. 아이디와 비밀번호를 다시 확인해주세요"),

    NOT_ENOUGH_WEBCLIENT_LOGIN_PARAM("AE002","API요청시 파라미터를 확인해주세요. 관리자에게 문의바랍니다.");

    private final String code;
    private final String message;
}
