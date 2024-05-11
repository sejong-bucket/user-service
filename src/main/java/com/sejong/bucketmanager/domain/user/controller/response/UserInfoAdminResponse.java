package com.sejong.bucketmanager.domain.user.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoAdminResponse {
    private UserInfo userInfo;
    private ReservationInfo reservationInfo;

}
