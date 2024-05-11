package com.sejong.bucketmanager.domain.locker.repository;

import com.sejong.bucketmanager.domain.locker.entity.Locker;
import com.sejong.bucketmanager.domain.major.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LockerJpaRepository extends JpaRepository<Locker,Long> {
    @Query("select L from LOCKER_TABLE as L join fetch L.major as M where M.id=:majorId")
    List<Locker> findByMajorId(@Param("majorId") Long majorId);

    Optional<Locker> findById(Long lockerId);
    @Query("select L from LOCKER_TABLE as L join fetch L.major as M where M=:major")
    List<Locker> findLockerByUserMajor(@Param("major") Major major);

}
