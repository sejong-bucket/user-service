package com.sejong.bucketmanager.domain.major.impl;

import com.sejong.bucketmanager.domain.major.entity.MajorDetail;
import com.sejong.bucketmanager.domain.major.repository.MajorDetailJpaRepository;
import com.sejong.bucketmanager.global.aop.meta.ImplService;
import com.sejong.bucketmanager.global.format.exception.major.majordetail.NotFoundMajorDetailException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ImplService
public class MajorDetailReader {
    private final MajorDetailJpaRepository majorDetailJpaRepository;

    public MajorDetail readByName(String name) {
        return majorDetailJpaRepository.findByNameWithMajor(name)
                .orElseThrow(() -> new NotFoundMajorDetailException());
    }
}
