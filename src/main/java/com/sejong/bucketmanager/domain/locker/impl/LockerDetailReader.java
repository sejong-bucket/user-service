package com.sejong.bucketmanager.domain.locker.impl;

import com.sejong.bucketmanager.domain.locker.entity.detail.LockerDetail;
import com.sejong.bucketmanager.domain.locker.repository.LockerDetailJpaRepository;
import com.sejong.bucketmanager.global.aop.meta.ImplService;
import com.sejong.bucketmanager.global.format.exception.locker.InvalidLockerDetailException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ImplService
public class LockerDetailReader {
    private final LockerDetailJpaRepository lockerDetailJpaRepository;
    public LockerDetail readWithLocker(Long id){
        return lockerDetailJpaRepository.findByIdWithLocker(id)
                .orElseThrow(InvalidLockerDetailException::new);
    }
    public List<LockerDetail> readAllByLockerId(Long lockerId){
        return lockerDetailJpaRepository.findAllByLockerId(lockerId);
    }
}
