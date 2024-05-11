package com.sejong.bucketmanager.domain.user.impl;

import com.sejong.bucketmanager.domain.major.entity.Major;
import com.sejong.bucketmanager.domain.user.entity.User;
import com.sejong.bucketmanager.domain.user.repository.UserJpaRepository;
import com.sejong.bucketmanager.domain.user.repository.UserQuerydslRepository;
import com.sejong.bucketmanager.global.aop.meta.ImplService;
import com.sejong.bucketmanager.global.format.exception.user.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

@RequiredArgsConstructor
@ImplService
public class UserReader {
    private final UserJpaRepository userJpaRepository;
    private final UserQuerydslRepository userQuerydslRepository;
    public User read(Long id) {
        return userJpaRepository.findById(id)
                .orElseThrow(NotFoundUserException::new);
    }

    public User readByStudentNumWithMajorDetailAndMajor(String studentNum){
        return userJpaRepository.findByStudentNumWithMajorDetailAndMajor(studentNum).orElse(null);
    }
    public User readWithMajorDetailAndMajor(Long id){
        return userJpaRepository.findByIdWithMajorDetailAndMajor(id)
                .orElseThrow(NotFoundUserException::new);
    }
    public Page<User>readInMajorAsc(Major major, String search, PageRequest pageRequest){
        return userQuerydslRepository.pagingAndSearchUserInMajorASC(major, search, pageRequest);
    }
}
