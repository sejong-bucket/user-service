package com.sejong.bucketmanager.global.format.exception.locker;


import com.sejong.bucketmanager.global.format.exception.ApplicationRunException;
import com.sejong.bucketmanager.global.format.exception.ErrorEnumCode;
import com.sejong.bucketmanager.global.format.exception.locker.errorCode.LockerErrorCode;

public class NotFoundLockerException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = LockerErrorCode.NOT_FOUND_LOCKER;
    private NotFoundLockerException(ErrorEnumCode errorEnumCode){
        super(errorEnumCode);
    }
    public NotFoundLockerException(){
        this(CODE);
    }
}