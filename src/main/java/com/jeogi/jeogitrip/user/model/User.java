package com.jeogi.jeogitrip.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private String userId;
    private String email;
    private char role;
}
