package com.sejong.bucketmanager.global.format.exception.user;

import com.sejong.bucketmanager.global.format.exception.ApplicationRunException;
import com.sejong.bucketmanager.global.format.exception.ErrorEnumCode;
import com.sejong.bucketmanager.global.format.exception.user.errorCode.UserErrorCode;

public class AlreadyReservedUserException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = UserErrorCode.ALREADY_RESERVED_USER;
    private AlreadyReservedUserException(ErrorEnumCode errorEnumCode){
        super(errorEnumCode);
    }
    public AlreadyReservedUserException(){
        this(CODE);
    }
}
