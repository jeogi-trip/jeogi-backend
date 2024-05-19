package com.jeogi.jeogitrip.user.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class User {
    private String userId;
    private String email;
    private String role;
}
