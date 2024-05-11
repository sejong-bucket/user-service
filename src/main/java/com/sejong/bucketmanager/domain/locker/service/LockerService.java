package com.sejong.bucketmanager.domain.locker.service;

import com.sejong.bucketmanager.domain.locker.controller.response.LockersInfoInMajorResponse;
import com.sejong.bucketmanager.domain.locker.controller.response.dto.LockersInfoDto;
import com.sejong.bucketmanager.domain.locker.controller.response.dto.LockersInfoInMajorDto;
import com.sejong.bucketmanager.domain.locker.entity.Locker;
import com.sejong.bucketmanager.domain.locker.entity.Period;
import com.sejong.bucketmanager.domain.locker.entity.detail.LockerDetail;
import com.sejong.bucketmanager.domain.locker.entity.detail.LockerDetailStatus;
import com.sejong.bucketmanager.domain.locker.entity.dto.LockerDetailInfo;
import com.sejong.bucketmanager.domain.locker.repository.LockerDetailJpaRepository;
import com.sejong.bucketmanager.domain.locker.repository.LockerJpaRepository;
import com.sejong.bucketmanager.domain.locker.service.dto.CreateLockerDetailDto;
import com.sejong.bucketmanager.domain.locker.service.dto.CreatedLockerInfo;
import com.sejong.bucketmanager.domain.locker.service.request.FindAllLockerInMajorRequestDto;
import com.sejong.bucketmanager.domain.locker.service.request.LockerCreateRequestDto;
import com.sejong.bucketmanager.domain.locker.service.request.ModifyLockerInfoReqeustDto;
import com.sejong.bucketmanager.domain.locker.service.response.LeftLockerResponseDto;
import com.sejong.bucketmanager.domain.locker.service.response.LockerCreateResponseDto;
import com.sejong.bucketmanager.domain.major.entity.Major;
import com.sejong.bucketmanager.domain.major.repository.MajorDetailJpaRepository;
import com.sejong.bucketmanager.domain.major.repository.MajorJpaRepository;
import com.sejong.bucketmanager.domain.reserve.entity.Reservation;
import com.sejong.bucketmanager.domain.reserve.repository.ReservationJpaRepository;
import com.sejong.bucketmanager.domain.user.entity.User;
import com.sejong.bucketmanager.domain.user.entity.UserState;
import com.sejong.bucketmanager.domain.user.entity.UserTier;
import com.sejong.bucketmanager.domain.user.repository.UserJpaRepository;
import com.sejong.bucketmanager.global.format.exception.locker.NotFoundLockerException;
import com.sejong.bucketmanager.global.format.exception.major.majordetail.NotFoundMajorDetailException;
import com.sejong.bucketmanager.global.format.exception.user.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class LockerService{
    private final LockerJpaRepository lockerJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final LockerDetailJpaRepository lockerDetailJpaRepository;
    private final MajorJpaRepository majorJpaRepository;
    private final LockerDetailService lockerDetailService;
    private final ReservationJpaRepository reservationJpaRepository;

    //남은 사물함 목록
    public LeftLockerResponseDto getCreatedLockers(Long majorId) {
        List<CreatedLockerInfo> createdLockerInfos = lockerJpaRepository.findByMajorId(majorId).stream()
                .map(locker -> CreatedLockerInfo.builder()
                        .id(locker.getId())
                        .startReservationTime(locker.getPeriod().getStartDateTime())
                        .endReservationTime(locker.getPeriod().getEndDateTime())
                        .totalColumn(locker.getTotalColumn())
                        .totalRow(locker.getTotalRow())
                        .permitStates(locker.getPermitUserState())
                        .image(locker.getImageUrl())
                        .name(locker.getName())
//                        .permitTiers(locker.getPermitUserTier())
                        .build())
                .collect(Collectors.toList());
        return LeftLockerResponseDto.builder()
                .createdLockerInfo(createdLockerInfos)
                .build();
    }


    public void modifyLockerInfo(ModifyLockerInfoReqeustDto reqeustDto) throws IOException {
        Locker locker = lockerJpaRepository.findById(reqeustDto.getLockerId()).orElseThrow(NotFoundLockerException::new);

//        changeImage(reqeustDto.getImage(), locker);

        changeReservationTime(reqeustDto.getStartTime(), reqeustDto.getEndTime(), locker);

        changeUserStatesCondition(reqeustDto.getUserStates(), locker);

//        changeUserTierCondition(reqeustDto.getUserTiers(), locker);

        changeLockerName(reqeustDto.getLockerName(), locker);
    }

/*    private void changeUserTierCondition(List<UserTier> newUserTiers, Locker locker) {
        if (newUserTiers == null || newUserTiers.isEmpty()) {
            return;
        }
        locker.getPermitUserTier().clear();
        newUserTiers.stream().forEach(userTier -> locker.getPermitUserTier().add(userTier));
    }*/

    private void changeUserStatesCondition(List<UserState> newUserStates, Locker locker) {
        if (newUserStates == null || newUserStates.isEmpty()) {
            return;
        }
        locker.getPermitUserState().clear();
        newUserStates.stream().forEach(userState -> locker.getPermitUserState().add(userState));
    }

    private void changeReservationTime(LocalDateTime newStartTime, LocalDateTime newEndTime, Locker locker) {
        if (newStartTime == null || newEndTime == null) {
            return;
        }
        locker.modifiedDateTime(Period.builder()
                .startDateTime(newStartTime)
                .endDateTime(newEndTime)
                .build());
    }


    /*private void changeImage(MultipartFile newImage, Locker locker) throws IOException {
        if (newImage == null || newImage.isEmpty()) {
            return;
        }
        String originalImageUrl = locker.getImageUrl();
        if (!originalImageUrl.isBlank()) {
            //기존 이미지 삭제
            imageFileAdminService.deleteImageToS3(originalImageUrl);
        }
        String newImageUrl = imageFileAdminService.saveImageToS3(newImage);
        locker.modifiedImageInfo(newImageUrl);
    }*/


    private void changeLockerName(String newLockerName, Locker locker) {
        if (newLockerName == null) {
            return;
        }
        locker.rename(newLockerName);
    }


    public LockersInfoInMajorResponse findAllLockerInMajor(FindAllLockerInMajorRequestDto requestDto) {
        log.info("사물함 전체 조회 --> 시작");
        User user = userJpaRepository.findByIdWithMajorDetailAndMajor(requestDto.getUserId())
                .orElseThrow(NotFoundUserException::new);

        Major major = user.getMajorDetail().getMajor();

        List<Locker> lockerByUserMajor = lockerJpaRepository.findByMajorId(major.getId());
        log.info("사물함 전체 조회 --> 끝");
        LockersInfoInMajorResponse response = LockersInfoInMajorResponse.builder()
                .lockersInfo(
                        lockerByUserMajor.stream()
                                .map(locker -> LockersInfoDto.builder()
                                        .lockerDetail(getLockerDetailInfos(locker))
                                        .locker(getLockersInfoInMajorDto(locker))
                                        .build()
                                ).collect(Collectors.toList()))
                .build();
        return response;
    }

    private List<LockerDetailInfo> getLockerDetailInfos(Locker locker) {
        return lockerDetailJpaRepository.findAllByLockerId(locker.getId()).stream()
                .map(
                        lockerDetail ->
                                getLockerDetailInfo(lockerDetail)
                ).collect(Collectors.toList());
    }

    private LockerDetailInfo getLockerDetailInfo(LockerDetail lockerDetail) {
        Optional<Reservation> reservation = reservationJpaRepository.findByLockerDetailId(lockerDetail.getId());
        return LockerDetailInfo.builder()
                .lockerNum(lockerDetail.getLockerNum())
                .columnNum(lockerDetail.getColumnNum())
                .rowNum(lockerDetail.getRowNum())
                .status(reservation.isPresent()? LockerDetailStatus.RESERVED:LockerDetailStatus.NON_RESERVED)
                .id(lockerDetail.getId())
                .build();
    }

    private static LockersInfoInMajorDto getLockersInfoInMajorDto(Locker locker) {
        return LockersInfoInMajorDto.builder()
                .id(locker.getId())
                .endReservationTime(locker.getPeriod().getEndDateTime())
                .startReservationTime(locker.getPeriod().getStartDateTime())
                .name(locker.getName())
                .totalColumn(locker.getTotalColumn())
                .totalRow(locker.getTotalRow())
//                .permitTiers(locker.getPermitUserTier().stream().map(tier -> tier.getName()).collect(Collectors.toList()))
                .permitStates(locker.getPermitUserState().stream().map(userState -> userState.getName()).collect(Collectors.toList()))
                .image(locker.getImageUrl())
                .build();
    }

    public LockerCreateResponseDto createLocker(LockerCreateRequestDto requestDto, Long majorId) throws IOException {
        // 이미지 삽입코드 주석처리
        /*String imageUrl = null;
        if (!requestDto.getImage().isEmpty()) { //이미지가 있을때
            imageUrl = imageFileAdminService.saveImageToS3(requestDto.getImage());
        }*/

        Major userMajor = majorJpaRepository.findById(majorId)
                .orElseThrow(NotFoundMajorDetailException::new);//에러 새로 만들어야함

        Locker createdLocker = Locker.createLocker(
                requestDto.toLockerCreateDto(
                        userMajor/*, imageUrl*/
                )
        );

        Locker saveLocker = lockerJpaRepository.save(createdLocker);

        lockerDetailService.createLockerDetails(CreateLockerDetailDto.builder()
                        .totalRow(Integer.valueOf(requestDto.getTotalRow()))
                        .totalColumn(Integer.valueOf(requestDto.getTotalColumn()))
                        .numberIncreaseDirection(requestDto.getNumberIncreaseDirection()).build(),
                createdLocker);

        return LockerCreateResponseDto.builder()
                .createdLockerId(saveLocker.getId())
                .createdLockerName(saveLocker.getName())
                .build();

    }
}
