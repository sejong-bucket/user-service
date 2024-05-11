package com.sejong.bucketmanager.domain.auth.controller;

import com.sejong.bucketmanager.domain.auth.controller.request.LoginRequest;
import com.sejong.bucketmanager.domain.auth.controller.response.LoginTokenResponse;
import com.sejong.bucketmanager.domain.auth.service.AuthService;
import com.sejong.bucketmanager.domain.auth.service.reponse.ReissueTokenResponseDto;
import com.sejong.bucketmanager.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Timer;

import static com.sejong.bucketmanager.global.format.success.SuccessResponseStatus.SUCCESS_LOGIN;
import static com.sejong.bucketmanager.global.format.success.SuccessResponseStatus.SUCCESS_LOGOUT;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.user.prefix}/auth")
class AuthController {
    private final AuthService authService;

    @Operation(
            summary = "로그인 기능",
            description = "세종대학교 학사정보시스템의 아이디와 비밀번호를 받아 로그인 하는 API"
    )
    @PostMapping("/login")
    public SuccessResponse<LoginTokenResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        log.info("{} : 로그인", loginRequest.getId());
        return new SuccessResponse(authService.login(loginRequest.toRequestDto()).toResponse(), SUCCESS_LOGIN);
    }

    @Operation(
            summary = "토큰 재발급 기능",
            description = "access token 만료시 refresh token을 이용하여 access token을 재발급받는 API"
    )
    @PostMapping("/reissue")
    public SuccessResponse<ReissueTokenResponseDto> reissue(@RequestHeader(value = "refreshToken") String refreshToken,
                                                            HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        return new SuccessResponse(authService.reissue(refreshToken));
    }

    @Operation(
            summary = "로그아웃 API"
    )
    @PostMapping("/logout")
    public SuccessResponse logout(@RequestHeader(value = "accessToken") String accessToken) {
        authService.logout(accessToken);
        return new SuccessResponse(SUCCESS_LOGOUT);
    }
}
