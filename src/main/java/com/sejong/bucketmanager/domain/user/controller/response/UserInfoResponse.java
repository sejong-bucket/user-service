package com.sejong.bucketmanager.domain.user.controller.response;

import com.sejong.bucketmanager.domain.user.entity.Role;
import com.sejong.bucketmanager.domain.user.entity.UserState;
import com.sejong.bucketmanager.domain.user.entity.UserTier;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponse {
    private UserPrimeKeySet userPrimeKeySet;
    private String userName;
    private String studentNum;
    private UserTier userTier;
    private UserState userState;
    private String reservedLockerDetailNum;
    private Long reservedLockerDetailId;
    private String reservedLockerName;
    private String majorDetail;

    @Builder
    @Getter
    public static class UserPrimeKeySet {
        private Long userId;
        private Long majorId;
        private Role role;
    }

    public static UserInfoResponse fromResponseDto(UserInfoQueryResponseDto userInfoQueryResponseDto) {
        return UserInfoResponse.builder()
                .userPrimeKeySet(UserPrimeKeySet.builder()
                        .userId(userInfoQueryResponseDto.getUserId())
                        .majorId(userInfoQueryResponseDto.getMajorId())
                        .build())
                .reservedLockerDetailNum(userInfoQueryResponseDto.getLockerDetailNum())
                .studentNum(userInfoQueryResponseDto.getStudentNum())
                .userName(userInfoQueryResponseDto.getName())
                .majorDetail(userInfoQueryResponseDto.getMajorDetail())
                .reservedLockerName(userInfoQueryResponseDto.getLockerName())
                .reservedLockerDetailId(userInfoQueryResponseDto.getLockerDetailId())
                .userTier(userInfoQueryResponseDto.getUserTier())
                .userState(userInfoQueryResponseDto.getUserState())
                .build();
    }
}
