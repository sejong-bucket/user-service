package com.sejong.bucketmanager.global.format.exception.user;


import com.sejong.bucketmanager.global.format.exception.ApplicationRunException;
import com.sejong.bucketmanager.global.format.exception.ErrorEnumCode;

import static com.sejong.bucketmanager.global.format.exception.user.errorCode.UserErrorCode.NOT_FOUND_USER;


public class NotFoundUserException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = NOT_FOUND_USER;


    private NotFoundUserException(ErrorEnumCode CODE) {
        super(CODE);
    }

    public NotFoundUserException() {
        this(CODE);
    }
}
