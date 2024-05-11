package com.sejong.bucketmanager.domain.major.repository;

import com.sejong.bucketmanager.domain.major.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorJpaRepository extends JpaRepository<Major,Long> {
}
