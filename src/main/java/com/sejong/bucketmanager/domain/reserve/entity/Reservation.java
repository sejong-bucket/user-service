package com.sejong.bucketmanager.domain.reserve.entity;

import com.sejong.bucketmanager.domain.common.entity.BaseTimeEntity;
import com.sejong.bucketmanager.domain.locker.entity.detail.LockerDetail;
import com.sejong.bucketmanager.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "RESERVATION_TABLE")
public class Reservation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locker_detail_id")
    private LockerDetail lockerDetail;
}
