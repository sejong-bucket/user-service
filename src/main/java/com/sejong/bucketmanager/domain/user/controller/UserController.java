package com.sejong.bucketmanager.domain.user.controller;

import com.sejong.bucketmanager.domain.user.controller.response.UserInfoQueryResponseDto;
import com.sejong.bucketmanager.domain.user.controller.response.UserInfoResponse;
import com.sejong.bucketmanager.domain.user.service.UserService;
import com.sejong.bucketmanager.domain.user.service.request.UserInfoRequestDto;
import com.sejong.bucketmanager.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.user.prefix}/users")
class UserController {

    private final UserService userService;

    @Operation(
           summary = "사용자 정보 조회",
            description = "마이페이지접속시 사용자 정보조회 API"
    )
    @GetMapping("/{userId}")
    public SuccessResponse<UserInfoResponse> findUserInfo(@PathVariable(name = "userId") Long userId) throws Exception {
        UserInfoQueryResponseDto userInfo = userService.findUserInfo(
                UserInfoRequestDto.builder()
                        .userId(userId)
                        .build());
        return new SuccessResponse(UserInfoResponse.fromResponseDto(
                userInfo
        ));
    }
}
