package com.sejong.bucketmanager.global.format.exception.webclient;


import com.sejong.bucketmanager.global.format.exception.ApplicationRunException;
import com.sejong.bucketmanager.global.format.exception.ErrorEnumCode;

import static com.sejong.bucketmanager.global.format.exception.webclient.errorCode.WebClientErrorCode.WEBCLIENT_AUTH_SERVER_ERROR;

public class WebClientAuthServerException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = WEBCLIENT_AUTH_SERVER_ERROR;

    private WebClientAuthServerException(ErrorEnumCode CODE) {
        super(CODE);
    }

    public WebClientAuthServerException() {
        this(CODE);
    }
}
