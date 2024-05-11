package com.sejong.bucketmanager.domain.user.service;

import com.sejong.bucketmanager.domain.locker.entity.detail.LockerDetail;
import com.sejong.bucketmanager.domain.major.entity.Major;
import com.sejong.bucketmanager.domain.major.impl.MajorReader;
import com.sejong.bucketmanager.domain.major.repository.MajorJpaRepository;
import com.sejong.bucketmanager.domain.reserve.entity.Reservation;
import com.sejong.bucketmanager.domain.reserve.impl.ReserveReader;
import com.sejong.bucketmanager.domain.reserve.service.ReservationService;
import com.sejong.bucketmanager.domain.user.controller.response.AllUserInfoForAdminResponseDto;
import com.sejong.bucketmanager.domain.user.controller.response.UserInfoQueryResponseDto;
import com.sejong.bucketmanager.domain.user.entity.User;
import com.sejong.bucketmanager.domain.user.impl.UserReader;
import com.sejong.bucketmanager.domain.user.repository.UserJpaRepository;
import com.sejong.bucketmanager.domain.user.repository.UserQuerydslRepository;
import com.sejong.bucketmanager.domain.user.service.request.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final ReservationService reservationService;
    private final ReserveReader reserveReader;
    private final MajorReader majorReader;
    private final int PAGE_SIZE = 30;
    private final MajorJpaRepository majorJpaRepository;
    private final UserQuerydslRepository userQuerydslRepository;
    private final UserReader userReader;
    /**
     * Todo
     * 학생회비 납부 신청자 조회기능
     * 관리자 기능 추후 테스트
     */
    /*public PagingApplyStudentsResponseDto findApplyStudentsInMajorByPage(Long userId, int page) {
        User user = userReader.readWithMajorDetailAndMajor(userId);
        Major major = user.getMajorDetail().getMajor();
        return getApplyStudentsResponsePageDto(page, major);
    }*/

    /**
     * Todo
     * 관리자 기능 추후 테스트
     */
    /*private PagingApplyStudentsResponseDto getApplyStudentsResponsePageDto(int page, Major major) {
        Page<User> membershipApplicants = userQuerydslRepository
                .findApplicantsByMajorOrderByStudentNumAsc(major, PageRequest.of(page, PAGE_SIZE));
        List<ApplyingStudentsDto> applicantInfos = membershipApplicants.stream()
                .map(applicant ->
                        ApplyingStudentsDto.builder()
                                .studentName(applicant.getName())
                                .studentNum(applicant.getStudentNum())
                                .build()
                ).collect(Collectors.toList());
        return PagingApplyStudentsResponseDto.builder()
                .currentPage(membershipApplicants.getNumber())
                .totalPage(membershipApplicants.getTotalPages())
                .applicant(applicantInfos).build();
    }*/

    public UserInfoQueryResponseDto findUserInfo(UserInfoRequestDto userRequestDto) {
        User user = userReader.readWithMajorDetailAndMajor(userRequestDto.getUserId());
        Optional<Reservation> reservation = getReservationByUserId(user);
        UserInfoQueryResponseDto userInfo = buildUserInfoResponse(user, reservation);
        return userInfo;
    }

    private UserInfoQueryResponseDto buildUserInfoResponse(User user, Optional<Reservation> maybeReservation) {
        UserInfoQueryResponseDto.UserInfoQueryResponseDtoBuilder userInfoBuilder = UserInfoQueryResponseDto.builder()
                .name(user.getName())
                .studentNum(user.getStudentNum())
                .userState(user.getUserState())
                .majorDetail(user.getMajorDetail().getName());

        setReservation(maybeReservation, userInfoBuilder);

        return userInfoBuilder.build();
    }

    private void setReservation(Optional<Reservation> maybeReservation, UserInfoQueryResponseDto.UserInfoQueryResponseDtoBuilder userInfoBuilder) {
        maybeReservation.ifPresent(reservation -> {
            LockerDetail lockerDetail = reservation.getLockerDetail();
            userInfoBuilder
                    .lockerName(lockerDetail.getLocker().getName())
                    .lockerDetailNum(lockerDetail.getLockerNum())
                    .lockerDetailId(lockerDetail.getId());
        });
    }

    public Page<AllUserInfoForAdminResponseDto> findAllUserInfoInMajor(FindAllUserRequestDto requestDto) {
        Major major = majorReader.read(requestDto.getMajorId());
        PageRequest pageRequest = PageRequest.of(requestDto.getPage(), PAGE_SIZE);
        Page<User> users = userReader.readInMajorAsc(major, requestDto.getSearch(), pageRequest);
        return pagingUsers(users);
    }



    private PageImpl<AllUserInfoForAdminResponseDto> pagingUsers(Page<User> allUser) {
        PageRequest pageRequest = PageRequest.of(allUser.getNumber(), allUser.getSize());

        Page<AllUserInfoForAdminResponseDto> userPageList = allUser.map(user -> {
            Optional<Reservation> reservation = getReservationByUserId(user);
            return AllUserInfoForAdminResponseDto.of(user, reservation);
        });

        return new PageImpl<>(userPageList.toList(), pageRequest, userPageList.getSize());
    }


    private Optional<Reservation> getReservationByUserId(User user) {
        return reserveReader.findByUserId(user.getId());
    }

    /**
     * Todo
     * 관리자 기능 추후 테스트
     *//*
    public UserTierResponseDto determineApplying(DetermineApplyingRequestDto requestDto, boolean isApprove) {
        User student = getMaybeUserByStudentNum(requestDto.getStudentNum())
                .orElseThrow(NotFoundUserException::new);
        if (isApprove) {
            student.approve();
        } else {
            student.deny();
        }
        return UserTierResponseDto.builder()
                .isApprove(isApprove)
                .build();
    }

    *//**
     * Todo
     * 프론트 구현X 및 관리자 기능 추후 테스트
     *//*
    public void applyMembership(Long userId) {
        User student = userJpaRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);
        verifyAlreadyMember(student);
        student.applyMembership();
    }

    private void verifyAlreadyMember(User student) {
        if (!student.getUserTier().equals(UserTier.NON_MEMBER)) {
            throw new IllegalStateException("이미 신청 또느 승인된 학우입니다.");
        }
    }*/

    /**
     * Todo
     * save, saveAll같이 update도 jpa의 트랜잭션을 묶는게 있는지 확인하고 성능 비교해보기
     * 벌크연산 적용하고, 성능비교하기
     *
     * @param requestDto
     * @throws Exception
     */
    /*public void modifiedUserInfo(ModifiedUserInfoRequestDto requestDto) throws Exception {
        for (ModifiedUserInfoDto modifiedUserInfo : requestDto.getModifiedUserInfoList()) {
            User user = getMaybeUserByStudentNum(modifiedUserInfo.getStudentNum())
                    .orElseThrow(NotFoundUserException::new);
            if (modifiedUserInfo.getLockerDetailId() != null) {
                reservationService.reserveForAdmin(
                        LockerRegisterRequestDto.builder() //일반예약은 lockerdetail의 PK값을 받아서 예약하는것이지만, 지금은 lockerdetail의 칸번호를 받고있으니 수정해야함
                                .userId(user.getId())
                                .lockerDetailId(modifiedUserInfo.getLockerDetailId())
                                .build()
                );
            }
            if (modifiedUserInfo.getAdmin() != null) {
                user.changeAdmin(modifiedUserInfo.getAdmin().booleanValue());
            }
            *//*if (modifiedUserInfo.getMembership() != null) {
                if (modifiedUserInfo.getMembership().booleanValue() == Boolean.TRUE) {//납부자로 변경하고싶을때
                    user.approve();
                } else {
                    user.deny();
                }
            }*//*
        }
    }*/

    /*public void updateUserDueInfoOrSave(List<UpdateUserDueInfoDto> updateUserDueInfoDto) throws Exception {
        List<User> newUsers = updateUserDueInfoDto.parallelStream()
                .filter(dto -> getMaybeUserByStudentNum(dto.getStudentNum()).isEmpty())
                .map(dto -> User.builder()
                        .name(dto.getName())
                        .studentNum(dto.getStudentNum())
                        .userTier(UserTier.judge(dto.isDue()))
                        .role(Role.ROLE_USER)
                        .majorDetail(dto.getMajorDetail())
                        .auth(false)
                        .build()).collect(Collectors.toList());
        userJpaRepository.saveAll(newUsers);
    }*/

    private Optional<User> getMaybeUserByStudentNum(String studentNum) {
        return userJpaRepository.findByStudentNum(studentNum);
    }
}
