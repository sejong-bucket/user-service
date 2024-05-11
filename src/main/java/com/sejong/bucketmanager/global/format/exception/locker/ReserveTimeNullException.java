package com.sejong.bucketmanager.global.format.exception.locker;


import com.sejong.bucketmanager.global.format.exception.ApplicationRunException;
import com.sejong.bucketmanager.global.format.exception.ErrorEnumCode;
import com.sejong.bucketmanager.global.format.exception.locker.errorCode.LockerErrorCode;

import static com.sejong.bucketmanager.global.format.exception.locker.errorCode.LockerErrorCode.RESESRVE_TIME_NULL;

public class ReserveTimeNullException extends ApplicationRunException {
    private static ErrorEnumCode CODE = RESESRVE_TIME_NULL;
    private ReserveTimeNullException(ErrorEnumCode errorEnumCode) {
        super(errorEnumCode);
    }
    public ReserveTimeNullException(){
        this(CODE);
    }
}
