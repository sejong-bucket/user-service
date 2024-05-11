package com.sejong.bucketmanager.global.format.exception.user;


import com.sejong.bucketmanager.global.format.exception.ApplicationRunException;
import com.sejong.bucketmanager.global.format.exception.ErrorEnumCode;

import static com.sejong.bucketmanager.global.format.exception.user.errorCode.UserErrorCode.INVALID_RESERVED_STATUS;

public class InvalidReservedStatusException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = INVALID_RESERVED_STATUS;

    private InvalidReservedStatusException(ErrorEnumCode code){
        super(code);
    }

    public InvalidReservedStatusException(){
        this(CODE);
    }
}
