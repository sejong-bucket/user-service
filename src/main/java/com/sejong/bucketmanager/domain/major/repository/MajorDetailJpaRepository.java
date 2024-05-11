package com.sejong.bucketmanager.domain.major.repository;

import com.sejong.bucketmanager.domain.major.entity.MajorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MajorDetailJpaRepository extends JpaRepository<MajorDetail,Long> {
    @Query("select MD from MAJOR_DETAIL_TABLE as MD join fetch MD.major where MD.name=:name")
    Optional<MajorDetail> findByNameWithMajor(@Param(value = "name") String name);
}
