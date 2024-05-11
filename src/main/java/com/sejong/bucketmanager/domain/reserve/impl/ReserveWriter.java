package com.sejong.bucketmanager.domain.reserve.impl;

import com.sejong.bucketmanager.domain.reserve.entity.Reservation;
import com.sejong.bucketmanager.domain.reserve.repository.ReservationJpaRepository;
import com.sejong.bucketmanager.global.aop.meta.ImplService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@ImplService
public class ReserveWriter {
    private final ReservationJpaRepository reservationJpaRepository;

    public Reservation save(Reservation reservation){
        return reservationJpaRepository.save(reservation);
    }
}
