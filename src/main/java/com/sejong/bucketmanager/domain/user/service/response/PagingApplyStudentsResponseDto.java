package com.sejong.bucketmanager.domain.user.service.response;

import com.sejong.bucketmanager.domain.user.controller.response.AllApplyingStudentPageResponse;
import lombok.Builder;

import java.util.List;

@Builder
public class PagingApplyStudentsResponseDto {
    private int currentPage;
    private int totalPage;
    private List<ApplyingStudentsDto> applicant;
    public AllApplyingStudentPageResponse toResponse(){
        return AllApplyingStudentPageResponse.builder()
                .applicant(applicant)
                .currentPage(currentPage)
                .totalPage(totalPage)
                .build();
    }
}
