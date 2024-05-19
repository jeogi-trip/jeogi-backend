package com.jeogi.jeogitrip.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResponse {
    private String accessToken;
    private String refreshToken;

    public static TokenResponse excludeEmailInDto(String accessToken, String refreshToken) {  // Builder 를 이용한 email 을 제외하고 TokenResponseDto 객체 생성, 일반 로그인
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
