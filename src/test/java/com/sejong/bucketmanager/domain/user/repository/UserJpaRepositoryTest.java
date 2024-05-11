package com.sejong.bucketmanager.domain.user.repository;

import com.sejong.bucketmanager.domain.major.entity.Major;
import com.sejong.bucketmanager.domain.major.entity.MajorDetail;
import com.sejong.bucketmanager.domain.major.repository.MajorDetailJpaRepository;
import com.sejong.bucketmanager.domain.major.repository.MajorJpaRepository;
import com.sejong.bucketmanager.domain.user.entity.Role;
import com.sejong.bucketmanager.domain.user.entity.User;
import com.sejong.bucketmanager.domain.user.entity.UserState;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
class UserJpaRepositoryTest {
    Major major;
    MajorDetail majorDetail;
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private MajorDetailJpaRepository majorDetailJpaRepository;
    @Autowired
    private MajorJpaRepository majorJpaRepository;
    @BeforeEach
    void init() {
        userJpaRepository.deleteAll();
    }
    @BeforeAll
    void setting(){
        major = majorJpaRepository.save(Major.builder()
                .name("testMajor")
                .build());
        majorDetail=majorDetailJpaRepository.save(MajorDetail.builder()
                .major(major)
                .build());
    }
    @AfterAll
    void deleteAll(){
        majorDetailJpaRepository.deleteAll();
        majorJpaRepository.deleteAll();
    }
    @DisplayName("학번으로 학생조회하는 jpa테스트")
    @Test
    void findByStudentNumTest() {
        //given
        String studentNum = "19011721";
        User testUser = createUser(studentNum, "test", "3");
        userJpaRepository.save(testUser);
        //when
        //then
        Optional<User> result = userJpaRepository.findByStudentNum(studentNum);
        Assertions.assertAll(()-> Assertions.assertNotNull(result),
                ()->org.assertj.core.api.Assertions.assertThat(result.get().getStudentNum()).isEqualTo(studentNum));
    }



    private User createUser(String studentNum, String test, String grade) {
        return User.builder()
                .studentNum(studentNum)
                .name(test)
                .userState(UserState.ATTEND)
                .grade(grade)
                .auth(true)
                .majorDetail(majorDetail)
                .role(Role.ROLE_USER)
                .build();
    }
}