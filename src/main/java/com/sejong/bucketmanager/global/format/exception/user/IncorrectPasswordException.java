package com.sejong.bucketmanager.global.format.exception.user;


import com.sejong.bucketmanager.global.format.exception.ApplicationRunException;
import com.sejong.bucketmanager.global.format.exception.ErrorEnumCode;

import static com.sejong.bucketmanager.global.format.exception.user.errorCode.UserErrorCode.INCORRECT_PASSWORD;

public class IncorrectPasswordException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = INCORRECT_PASSWORD;
    private IncorrectPasswordException(ErrorEnumCode errorEnumCode){
        super(errorEnumCode);
    }
    public IncorrectPasswordException(){
        this(CODE);
    }
}
