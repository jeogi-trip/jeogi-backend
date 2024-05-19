package com.jeogi.jeogitrip.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class UserGeneral {
    private int userGeneralId;
    private String userId;
    private String password;
    private boolean auto;
}
