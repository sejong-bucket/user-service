package com.sejong.bucketmanager.domain.locker.entity;

import com.sejong.bucketmanager.domain.common.entity.BaseTimeEntity;
import com.sejong.bucketmanager.domain.locker.entity.dto.LockerCreateDto;
import com.sejong.bucketmanager.domain.major.entity.Major;
import com.sejong.bucketmanager.domain.user.entity.UserState;
import com.sejong.bucketmanager.domain.user.entity.UserTier;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static java.time.LocalDateTime.now;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "LOCKER_TABLE")
public class Locker extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(name = "사물함 예약 기간")
    @Embedded
    private Period period;

    @Schema(name = "사물함명")
    private String name;

    @Schema(name = "사물함 보유 학과")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    private String imageUrl;
    private String totalRow;
    private String totalColumn;

    @ElementCollection(targetClass = UserState.class)
    @JoinTable(name = "PERMIT_USER_STATE_TABLE", joinColumns = @JoinColumn(name = "locker_id"))
    @Column(name = "permitUserState")
    @Enumerated(EnumType.STRING)
    private List<UserState> permitUserState = new ArrayList<>();

    /*@ElementCollection(targetClass = UserTier.class)
    @JoinTable(name = "PERMIT_USER_Tier_TABLE", joinColumns = @JoinColumn(name = "locker_id"))
    @Column(name = "permitUserTier")
    @Enumerated(EnumType.STRING)
    private List<UserTier> permitUserTier = new ArrayList<>();*/

    /*public void modifiedDateTime(Period period) {
        this.period = period;
    }

    public void modifiedImageInfo(String newImageUrl) {
        this.imageUrl=newImageUrl;
    }


   *//* public static Locker createLocker(LockerCreateDto lockercreateDto) {
        return Locker.builder()
                .period(new Period(lockercreateDto.getStartReservationTime(), lockercreateDto.getEndReservationTime()))
                .name(lockercreateDto.getLockerName())
                .major(lockercreateDto.getMajor())
                .totalColumn(lockercreateDto.getTotalColumn())
                .totalRow(lockercreateDto.getTotalRow())
                .imageUrl(lockercreateDto.getImageUrl())
                .permitUserTier(lockercreateDto.getUserTiers())
                .permitUserState(lockercreateDto.getUserStates())
                .build();
    }*/

    public boolean isDeadlineValid() {
        return this.period.getEndDateTime().isAfter(now()) &&
                this.period.getStartDateTime().isBefore(now());
    }
    public void rename(String rename){
        this.name = rename;
    }

    public void modifiedDateTime(Period period) {
        this.period = period;
    }

    public void modifiedImageInfo(String newImageUrl) {
        this.imageUrl=newImageUrl;
    }


    public static Locker createLocker(LockerCreateDto lockercreateDto) {
        return Locker.builder()
                .period(new Period(lockercreateDto.getStartReservationTime(), lockercreateDto.getEndReservationTime()))
                .name(lockercreateDto.getLockerName())
                .major(lockercreateDto.getMajor())
                .totalColumn(lockercreateDto.getTotalColumn())
                .totalRow(lockercreateDto.getTotalRow())
                .imageUrl(lockercreateDto.getImageUrl())
                .permitUserState(lockercreateDto.getUserStates())
                .build();
    }
}
