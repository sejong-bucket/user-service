package com.sejong.bucketmanager.global.format.exception.reservation;


import com.sejong.bucketmanager.global.format.exception.ApplicationRunException;
import com.sejong.bucketmanager.global.format.exception.ErrorEnumCode;

import static com.sejong.bucketmanager.global.format.exception.reservation.errorCode.ReservationErrorCode.NOT_FOUND_RESERVATION;

public class NotFoundReservationException extends ApplicationRunException {
    private final static ErrorEnumCode CODE=NOT_FOUND_RESERVATION;

    private NotFoundReservationException(ErrorEnumCode errorEnumCode) {
        super(errorEnumCode);
    }
    public NotFoundReservationException(){
        this(CODE);
    }
}
