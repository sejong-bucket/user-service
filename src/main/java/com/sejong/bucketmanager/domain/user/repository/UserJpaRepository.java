package com.sejong.bucketmanager.domain.user.repository;

import com.sejong.bucketmanager.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User,Long> {
    Optional<User> findByStudentNum(String studentNum);
    @Query("select U from USER_TABLE as U join fetch U.majorDetail as MD join fetch MD.major where U.id = :userId")
    Optional<User> findByIdWithMajorDetailAndMajor(@Param("userId") Long userId);

    @Query("select U from USER_TABLE as U join fetch U.majorDetail as MD join fetch MD.major where U.studentNum = :studentNum")
    Optional<User> findByStudentNumWithMajorDetailAndMajor(@Param("studentNum") String studentNum);
}
