package com.sejong.bucketmanager.domain.major.impl;

import com.sejong.bucketmanager.domain.major.entity.Major;
import com.sejong.bucketmanager.domain.major.entity.MajorDetail;
import com.sejong.bucketmanager.domain.major.repository.MajorJpaRepository;
import com.sejong.bucketmanager.global.aop.meta.ImplService;
import com.sejong.bucketmanager.global.format.exception.major.majordetail.NotFoundMajorDetailException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ImplService
public class MajorReader {
    private final MajorJpaRepository majorJpaRepository;

    public Major read(Long id) {
        return majorJpaRepository.findById(id)
                .orElseThrow(NotFoundMajorDetailException::new);
    }
}
