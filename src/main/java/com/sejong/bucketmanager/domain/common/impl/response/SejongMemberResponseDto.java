package com.sejong.bucketmanager.domain.common.impl.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SejongMemberResponseDto {
    private String msg;
    private SejongMemberResponseResult result;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public class SejongMemberResponseResult {
        private SejongMemberResponseBody body;
        private String is_auth;

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        public class SejongMemberResponseBody {
            private String name;
            private String grade;
            private String status;
            private String message;
            private String major;
        }
    }
}
