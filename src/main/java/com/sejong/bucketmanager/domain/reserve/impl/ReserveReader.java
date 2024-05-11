package com.sejong.bucketmanager.domain.reserve.impl;

import com.sejong.bucketmanager.domain.locker.entity.detail.LockerDetail;
import com.sejong.bucketmanager.domain.reserve.entity.Reservation;
import com.sejong.bucketmanager.domain.reserve.repository.ReservationJpaRepository;
import com.sejong.bucketmanager.global.aop.meta.ImplService;
import lombok.RequiredArgsConstructor;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@ImplService
public class ReserveReader {
    private final ReservationJpaRepository reservationJpaRepository;
    public List<Reservation> findAllByLockerDetails(List<LockerDetail>lockerDetails){
        return reservationJpaRepository.findAllByLockerDetails(lockerDetails);

    }
    public Optional<Reservation>findByLockerDetailId(Long lockerDetailId){
        return reservationJpaRepository.findByLockerDetailId(lockerDetailId);
    }
    public Optional<Reservation >findByUserId(Long userId){
        return reservationJpaRepository.findByUserId(userId);
    }
}
