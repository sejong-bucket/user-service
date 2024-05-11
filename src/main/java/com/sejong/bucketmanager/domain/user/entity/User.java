package com.sejong.bucketmanager.domain.user.entity;

import com.sejong.bucketmanager.domain.auth.service.dto.UpdateUserInfoDto;
import com.sejong.bucketmanager.domain.common.entity.BaseTimeEntity;
import com.sejong.bucketmanager.domain.major.entity.MajorDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.sejong.bucketmanager.domain.user.entity.Role.ROLE_ADMIN;
import static com.sejong.bucketmanager.domain.user.entity.Role.ROLE_USER;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Builder
@Entity(name = "USER_TABLE")
//@Table(indexes = @Index(name = "idx_studentNum",columnList = "STUDENT_NUM"))
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "STUDENT_NUM")
    private String studentNum;

    @Enumerated(STRING)
    private UserState userState;

    @Enumerated(STRING)
    @Column(nullable = false)
    private Role role;
    private String grade;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "major_detail_id")
    private MajorDetail majorDetail;
    private boolean auth;
    public void changeAdmin(boolean isAdmin) {
        if (isAdmin) {
            this.role = ROLE_ADMIN;
        } else {
            this.role = ROLE_USER;
        }
    }
    public User updateUserInfo(UpdateUserInfoDto updateUserInfoDto) {
        this.auth = updateUserInfoDto.isAuth();
        this.userState = updateUserInfoDto.getStatus();
        this.grade = updateUserInfoDto.getGrade();
        this.majorDetail = updateUserInfoDto.getMajorDetail();
        return this;
    }

    /*public UserTier applyMembership() {
        userTier = UserTier.APPLICANT;
        return userTier;
    }

    public UserTier approve() {
        userTier = UserTier.MEMBER;
        return userTier;
    }

    public UserTier deny() {
        userTier = NON_MEMBER;
        return userTier;
    }*/
}
