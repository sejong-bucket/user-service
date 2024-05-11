package com.sejong.bucketmanager.domain.locker.entity.detail;

import com.sejong.bucketmanager.domain.common.entity.BaseTimeEntity;
import com.sejong.bucketmanager.domain.locker.entity.Locker;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity(name = "LOCKER_DETAIL_TABLE")
public class LockerDetail extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rowNum;
    private String columnNum;
    private String lockerNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locker_id")
    private Locker locker;
}
