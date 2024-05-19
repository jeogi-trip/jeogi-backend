package com.jeogi.jeogitrip.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class UserDetail {
    private int userDetailId;
    private String userId;
    private String name;
    private String phone;
    private String birth;
}
