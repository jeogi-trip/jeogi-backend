package com.jeogi.jeogitrip.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserRequest {
    private String userId;
    private String password;
    private String email;
    private String name;
    private String phone;
    private String birth;
}
