package com.jeogi.jeogitrip.user.model.service;


import com.jeogi.jeogitrip.user.model.LoginRequest;
import com.jeogi.jeogitrip.user.model.TokenResponse;
import com.jeogi.jeogitrip.user.model.UserRequest;

public interface AuthService {
    int signUp(UserRequest userRequest);
    TokenResponse signIn(LoginRequest loginRequest);
}
