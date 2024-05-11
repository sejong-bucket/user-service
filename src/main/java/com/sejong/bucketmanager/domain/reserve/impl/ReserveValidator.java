package com.sejong.bucketmanager.domain.reserve.impl;

import com.sejong.bucketmanager.domain.locker.entity.Locker;
import com.sejong.bucketmanager.domain.locker.entity.detail.LockerDetail;
import com.sejong.bucketmanager.domain.reserve.entity.Reservation;
import com.sejong.bucketmanager.domain.user.entity.Role;
import com.sejong.bucketmanager.domain.user.entity.User;
import com.sejong.bucketmanager.global.aop.meta.ImplService;
import com.sejong.bucketmanager.global.format.exception.locker.AlreadyReservedLockerException;
import com.sejong.bucketmanager.global.format.exception.locker.IsNotReserveTimeException;
import com.sejong.bucketmanager.global.format.exception.reservation.NotMatchUserTierAndLockerException;
import com.sejong.bucketmanager.global.format.exception.user.AlreadyReservedUserException;
import com.sejong.bucketmanager.global.format.exception.user.InvalidReservedStatusException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@ImplService
public class ReserveValidator {
    private final ReserveReader reserveReader;

    public void verifyCommonConditionForReserve(User user, LockerDetail lockerDetail,Locker locker) {
        if(user.getRole().equals(Role.ROLE_USER)){
            verifyDistinctConditionForReserve(locker);
        }
        verifyLockerConditions(user, lockerDetail, locker);
        verifyUserConditions(user);
    }
    private void verifyDistinctConditionForReserve(Locker locker) {
        if (!locker.isDeadlineValid()) {
            throw new IsNotReserveTimeException();
        }
    }
    private void verifyUserConditions(User user) {
        if (isReservationNegativeById(user.getId())) {
            throw new AlreadyReservedUserException();
        }
    }

    private void verifyLockerConditions(User user, LockerDetail lockerDetail, Locker locker) {
        if (!locker.getPermitUserState().contains(user.getUserState())) {
            throw new InvalidReservedStatusException();
        }
        if (isReservationNegativeByLockerDetailId(lockerDetail.getId())) {
            throw new AlreadyReservedLockerException();
        }
    }

    private boolean isReservationNegativeByLockerDetailId(Long lockerDetailId) {
        Optional<Reservation> reservation = reserveReader.findByLockerDetailId(lockerDetailId);
        if (reservation.isPresent()) {
            return true;
        }
        return false;
    }

    private boolean isReservationNegativeById(Long userId) {
        Optional<Reservation> reservation = reserveReader.findByUserId(userId);
        if (reservation.isPresent()) {
            return true;
        }
        return false;
    }
}
