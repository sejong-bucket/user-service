package com.sejong.bucketmanager.domain.user.controller;

import com.sejong.bucketmanager.domain.user.controller.response.AllUserInfoForAdminResponseDto;
import com.sejong.bucketmanager.domain.user.controller.response.UserInfoAdminPageResponse;
import com.sejong.bucketmanager.domain.user.service.UserService;
import com.sejong.bucketmanager.domain.user.service.request.FindAllUserRequestDto;
import com.sejong.bucketmanager.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.admin.prefix}")
public class UserAdminController {

    private final UserService userService;

    @Operation(
            summary = "모든 사용자의 정보를 조회",
            description = "모든 사용자의 정보를 조회하여 반환해주는 API(관리자용),검색기능 추가예정,uri 수정될수 있음"
    )
    /*@ImplicitParam(
            name = "page"
            , value = "원하는 페이지번호"
            , required = true
            , dataType = "int"
            , defaultValue = "0")*/
    @GetMapping("/majors/{majorId}/users")
    public SuccessResponse<UserInfoAdminPageResponse> adminInfo(@PathVariable("majorId") Long majorId,
                                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                                @RequestParam(name = "search", required = false) String search) {
        Page<AllUserInfoForAdminResponseDto> allUserInfo = userService.findAllUserInfoInMajor(FindAllUserRequestDto
                .of(majorId, search, page));
        return new SuccessResponse(
                UserInfoAdminPageResponse.builder()
                        .adminResponse(allUserInfo.stream()
                                .map(allUserInfoForAdminResponseDto -> allUserInfoForAdminResponseDto.toResponse())
                                .collect(Collectors.toList()))
                        .currentPage(allUserInfo.getNumber())
                        .totalPage(allUserInfo.getTotalPages())
                        .currentElementSize(allUserInfo.getNumberOfElements())
                        .build()
        );

    }

/*
    @Operation(
            summary = "수정된 사용자의 정보를 받아 실제 dB에 업데이트해주는 API(관리자용)"
    )
    @PatchMapping("/users")
    public SuccessResponse modifiedUserInfo(@Valid @RequestBody ModifiedUserInfoRequest modifiedUserInfoRequest)
            throws Exception {
        userService.modifiedUserInfo(modifiedUserInfoRequest.toRequestDto());
        return SuccessResponse.ok();
    }
*/

   /* @ApiImplicitParam(
            name = "page"
            , value = "원하는 페이지번호"
            , required = true
            , dataType = "int"
            , defaultValue = "0")*/
    /*@Operation(summary = "학생회비 납부 신청자 조회")
    @GetMapping("/users/{userId}tier/apply")
    public SuccessResponse<AllApplyingStudentPageResponse> findAllApplyingStudent(@PathVariable Long userId, @RequestParam(name = "page",
            defaultValue = "0") int page) {
        AllApplyingStudentPageResponse response = userService.findApplyStudentsInMajorByPage(userId, page)
                .toResponse();
        return new SuccessResponse(response);
    }*/

    /*@Operation(summary = "학생회비 납부 신청자 승인 및 거절 API")
    *//*@ApiImplicitParam(
            name = "isApprove"
            , value = "학생회비 납부 승인 또는 거절하기 위한 boolean값"
            , required = true
            , dataType = "boolean")*//*
    @PostMapping("/users/tier/apply")
    public SuccessResponse<UserTierResponse> determineApplying(@Valid @RequestBody DetermineApplyingRequest request,
                                                               @RequestParam(name = "isApprove") boolean isApprove) {
        UserTierResponse response = userService.determineApplying(request.toRequestDto(), isApprove).toResponse();
        return new SuccessResponse(response);
    }*/
}
