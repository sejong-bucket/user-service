package com.sejong.bucketmanager.domain.reserve.service;

import com.sejong.bucketmanager.domain.locker.entity.Locker;
import com.sejong.bucketmanager.domain.locker.entity.detail.LockerDetail;
import com.sejong.bucketmanager.domain.locker.impl.LockerDetailReader;
import com.sejong.bucketmanager.domain.locker.service.request.LockerRegisterRequestDto;
import com.sejong.bucketmanager.domain.locker.service.response.LockerRegisterResponseDto;
import com.sejong.bucketmanager.domain.reserve.entity.Reservation;
import com.sejong.bucketmanager.domain.reserve.impl.ReserveDeleter;
import com.sejong.bucketmanager.domain.reserve.impl.ReserveReader;
import com.sejong.bucketmanager.domain.reserve.impl.ReserveRunner;
import com.sejong.bucketmanager.domain.reserve.service.req.ChangeReservationRequestDto;
import com.sejong.bucketmanager.domain.user.entity.User;
import com.sejong.bucketmanager.domain.user.impl.UserReader;
import com.sejong.bucketmanager.domain.user.service.request.UserCancelLockerRequestDto;
import com.sejong.bucketmanager.global.aop.meta.DistributeLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ReservationService {
    private final UserReader userReader;
    private final LockerDetailReader lockerDetailReader;
    private final ReserveReader reserveReader;
    private final ReserveRunner reserveRunner;
    private final ReserveDeleter reserveRemover;
    private static final String LOCKER_KEY = "LOCKER_";

    public void resetReservation(Long lockerId) {
        List<LockerDetail> lockerDetails = lockerDetailReader.readAllByLockerId(lockerId);

        List<Reservation> reservations = reserveReader.findAllByLockerDetails(lockerDetails);

        reserveRemover.runReset(reservations);
    }

    /*@DistributeLock(identifier = LOCKER_KEY, key = "#dto.lockerDetailId")
    public LockerRegisterResponseDto reserveForAdmin(LockerRegisterRequestDto dto) throws Exception {
        User user = getUserById(dto.getUserId());
        LockerDetail lockerDetail = getLockerDetailById(dto.getLockerDetailId());
        log.info("(사용자)예약 시작 : [학번 {}, 사물함 번호 {}]", user.getStudentNum(), lockerDetail.getLockerNum());
        Locker locker = getLockerFromLockerDetail(lockerDetail);
        reserveRunner.reserveForAdmin(); //조건 다르게 해야하는데, 메서드를 따로 만들긴 싫고,,,
        return LockerRegisterResponseDto.of((lockerDetail.getLockerNum()),
                user.getStudentNum(),
                getLockerFromLockerDetail(lockerDetail).getName());
    }*/

    @DistributeLock(identifier = LOCKER_KEY, key = "#dto.lockerDetailId")
    public LockerRegisterResponseDto reserveForUser(LockerRegisterRequestDto dto) throws Exception {
        User user = getUserById(dto.getUserId());
        LockerDetail lockerDetail = getLockerDetailById(dto.getLockerDetailId());
        log.info("(사용자)예약 시작 : [학번 {}, 사물함 번호 {}]", user.getStudentNum(), lockerDetail.getLockerNum());
        Locker locker = getLockerFromLockerDetail(lockerDetail);
        reserveRunner.reserve(user, lockerDetail, locker);
        log.info("예약 완료 : [학번 {}, 사물함 번호 {}]", user.getStudentNum(), lockerDetail.getLockerNum());
        return LockerRegisterResponseDto
                .of(lockerDetail.getLockerNum(), user.getStudentNum(), locker.getName());
    }

    private User getUserById(Long userId) {
        return userReader.read(userId);
    }

    private LockerDetail getLockerDetailById(Long lockerDetailId) {
        return lockerDetailReader.readWithLocker(lockerDetailId);
    }

    private Locker getLockerFromLockerDetail(LockerDetail lockerDetail) {
        return lockerDetail.getLocker();
    }


    public void cancelReserve(UserCancelLockerRequestDto cancelLockerDto) {
        log.info("{} : 사물함 취소시작", cancelLockerDto.getUserId());

        reserveRemover.cancelReserve(cancelLockerDto.getUserId(), cancelLockerDto.getLockerDetailId());

        log.info("{} : 사물함 취소끝", cancelLockerDto.getUserId());
    }

    @DistributeLock(identifier = LOCKER_KEY, key = "#dto.newLockerDetailId")
    public void changeReservation(ChangeReservationRequestDto dto) {
        User user = getUserById(dto.getUserId());
        LockerDetail newLockerDetail = getLockerDetailById(dto.getNewLockerDetailId());
        LockerDetail originLockerDetail = getLockerDetailById(dto.getOriginLockerDetailId());
        Locker newLocker = getLockerFromLockerDetail(newLockerDetail);
        reserveRunner.changeReserve(user, newLockerDetail, originLockerDetail, newLocker);
    }
}
