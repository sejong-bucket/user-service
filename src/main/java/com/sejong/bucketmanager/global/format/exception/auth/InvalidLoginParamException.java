package com.sejong.bucketmanager.global.format.exception.auth;


import com.sejong.bucketmanager.global.format.exception.ApplicationRunException;
import com.sejong.bucketmanager.global.format.exception.ErrorEnumCode;

import static com.sejong.bucketmanager.global.format.exception.auth.errorCode.AuthErrorCode.INVALID_LOGIN_PARAM;

public class InvalidLoginParamException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = INVALID_LOGIN_PARAM;

    public InvalidLoginParamException(){
        this(CODE);
    }
    private InvalidLoginParamException(ErrorEnumCode errorEnumCode) {
        super(errorEnumCode);
    }
}
