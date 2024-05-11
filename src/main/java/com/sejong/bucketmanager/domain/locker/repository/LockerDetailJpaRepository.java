package com.sejong.bucketmanager.domain.locker.repository;

import com.sejong.bucketmanager.domain.locker.entity.detail.LockerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LockerDetailJpaRepository extends JpaRepository<LockerDetail,Long> {
    List<LockerDetail> findAllByLockerId(Long lockerId);
    @Query("SELECT LD FROM LOCKER_DETAIL_TABLE LD JOIN FETCH LD.locker L WHERE LD.id = :lockerDetailId")
    Optional<LockerDetail> findByIdWithLocker(@Param("lockerDetailId")Long lockerDetailId);
}
