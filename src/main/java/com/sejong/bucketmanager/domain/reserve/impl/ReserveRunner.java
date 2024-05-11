package com.sejong.bucketmanager.domain.reserve.impl;

import com.sejong.bucketmanager.domain.locker.entity.Locker;
import com.sejong.bucketmanager.domain.locker.entity.detail.LockerDetail;
import com.sejong.bucketmanager.domain.reserve.entity.Reservation;
import com.sejong.bucketmanager.domain.user.entity.User;
import com.sejong.bucketmanager.global.aop.meta.ImplService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ImplService
public class ReserveRunner {
    private final ReserveWriter reserveWriter;
    private final ReserveDeleter reserveDeleter;
    private final ReserveValidator reserveValidator;
    public void reserve(User user, LockerDetail lockerDetail, Locker locker){
        reserveValidator.verifyCommonConditionForReserve(user,lockerDetail,locker);
        reserveLockerDetail(user, lockerDetail);
    }

    public void changeReserve(User user,LockerDetail newLockerDetail,LockerDetail originLockerDetail,Locker newLocker){
        reserveValidator.verifyCommonConditionForReserve(user, newLockerDetail, newLocker);
        reserveDeleter.cancelReserve(user.getId(),originLockerDetail.getId());
        reserveLockerDetail(user, newLockerDetail);
    }
    private void reserveLockerDetail(User user, LockerDetail lockerDetail) {
        reserveWriter.save(Reservation.builder()
                .lockerDetail(lockerDetail)
                .user(user)
                .build());
    }
}
