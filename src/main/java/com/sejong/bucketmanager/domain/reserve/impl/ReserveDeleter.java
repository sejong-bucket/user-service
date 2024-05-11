package com.sejong.bucketmanager.domain.reserve.impl;

import com.sejong.bucketmanager.domain.reserve.entity.Reservation;
import com.sejong.bucketmanager.domain.reserve.repository.ReservationJpaRepository;
import com.sejong.bucketmanager.global.aop.meta.ImplService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@ImplService
public class ReserveDeleter {
    private final ReservationJpaRepository reservationJpaRepository;

    public void cancelReserve(Long userId,Long lockerDetailId) {
        reservationJpaRepository.deleteByUserAndLockerId(userId,lockerDetailId);
    }
    public void runReset(List<Reservation> reservations) {
        List<Long> reservationIds = reservations.stream().map(reservation -> reservation.getId()).collect(toList());
        reservationJpaRepository.deleteAllByIds(reservationIds);
    }
}
