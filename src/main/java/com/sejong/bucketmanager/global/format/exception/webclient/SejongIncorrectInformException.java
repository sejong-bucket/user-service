package com.sejong.bucketmanager.global.format.exception.webclient;


import com.sejong.bucketmanager.global.format.exception.ApplicationRunException;
import com.sejong.bucketmanager.global.format.exception.ErrorEnumCode;

import static com.sejong.bucketmanager.global.format.exception.webclient.errorCode.WebClientErrorCode.SEJONG_INCORRECT_INFORM;

public class SejongIncorrectInformException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = SEJONG_INCORRECT_INFORM;
    public SejongIncorrectInformException(){
        this(CODE);
    }
    private SejongIncorrectInformException(ErrorEnumCode CODE){
        super(CODE);
    }
}
