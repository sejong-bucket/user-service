package com.sejong.bucketmanager.domain.major.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity(name = "MAJOR_DETAIL_TABLE")
public class MajorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "major_id")
    private Major major;
}
