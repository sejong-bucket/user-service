package com.sejong.bucketmanager.domain.reserve.controller.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ChangeReservationResponse {
    private Long changeLockerId;
}
