package com.jeogi.jeogitrip.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long refreshTokenExpiresIn;
}
