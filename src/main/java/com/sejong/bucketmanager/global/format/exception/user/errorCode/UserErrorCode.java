package com.sejong.bucketmanager.global.format.exception.user.errorCode;


import com.sejong.bucketmanager.global.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorEnumCode {
    NOT_FOUND_USER("U001", "사용자를 찾지 못했습니다"),
    ALREADY_RESERVED_USER("U002", "이미 사물함을 예약한 사용자 입니다."),
    INCORRECT_PASSWORD("U003","원래 비밀번호가 다릅니다."),
    INVALID_RESERVED_STATUS("U004","재학생만 사물함을 이용할수 있습니다.");
    private final String code;
    private final String message;
}
