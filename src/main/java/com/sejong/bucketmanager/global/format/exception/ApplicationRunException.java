package com.sejong.bucketmanager.global.format.exception;

import lombok.Getter;

@Getter
public class ApplicationRunException extends RuntimeException{
    private ErrorEnumCode errorEnumCode;
    public ApplicationRunException(ErrorEnumCode errorEnumCode){
        super(errorEnumCode.getMessage());
        this.errorEnumCode = errorEnumCode;
    }
}
