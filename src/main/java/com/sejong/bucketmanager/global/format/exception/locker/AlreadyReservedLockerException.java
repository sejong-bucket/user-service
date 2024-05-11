package com.sejong.bucketmanager.global.format.exception.locker;


import com.sejong.bucketmanager.global.format.exception.ApplicationRunException;
import com.sejong.bucketmanager.global.format.exception.ErrorEnumCode;
import com.sejong.bucketmanager.global.format.exception.locker.errorCode.LockerErrorCode;

public class AlreadyReservedLockerException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = LockerErrorCode.ALREADY_RESERVERD_LOCKER;
    private AlreadyReservedLockerException(ErrorEnumCode errorEnumCode){
        super(errorEnumCode);
    }

    public AlreadyReservedLockerException(){
        this(CODE);
    }
}
