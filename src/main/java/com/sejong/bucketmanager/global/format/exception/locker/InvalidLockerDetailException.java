package com.sejong.bucketmanager.global.format.exception.locker;


import com.sejong.bucketmanager.global.format.exception.ApplicationRunException;
import com.sejong.bucketmanager.global.format.exception.ErrorEnumCode;

import static com.sejong.bucketmanager.global.format.exception.locker.errorCode.LockerErrorCode.INVALID_LOCKER_DETAIL;

public class InvalidLockerDetailException extends ApplicationRunException {
    private final static ErrorEnumCode CODE = INVALID_LOCKER_DETAIL;
    public InvalidLockerDetailException(ErrorEnumCode CODE){
        super(CODE);
    }

    public InvalidLockerDetailException() {
        this(CODE);
    }
}
