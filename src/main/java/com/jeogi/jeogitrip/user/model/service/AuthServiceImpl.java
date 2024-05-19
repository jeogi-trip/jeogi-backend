package com.jeogi.jeogitrip.user.model.service;

import com.jeogi.jeogitrip.config.jwt.JwtProvider;
import com.jeogi.jeogitrip.user.model.*;
import com.jeogi.jeogitrip.user.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    @Override
    public int signUp(UserRequest userRequest) {
        try{
            int userInserted = userMapper.insertUser(toUser(userRequest.getUserId(),userRequest.getEmail()));
            int userDetailInserted = userMapper.insertUserDetail(toUserDetail(userRequest.getUserId(),userRequest.getName(),userRequest.getPhone(),userRequest.getBirth()));
            int userGeneralInserted = userMapper.insertUserGeneral(toUserGeneral(userRequest.getUserId(),userRequest.getPassword()));
            return userInserted+userDetailInserted+userGeneralInserted;
        }catch (Exception e){
            throw new RuntimeException("회원 등록 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public TokenResponse signIn(LoginRequest loginRequest) {
        String userId = loginRequest.getUserId();
        String email = userMapper.selectEmailByUserId(userId);
        String a = loginRequest.getPassword();
        String b = userMapper.selectUserByUserId(userId).getPassword();
        System.out.println(a+", "+b);
//        if(!passwordEncoder.matches(loginRequest.getPassword(), userMapper.selectUserByUserId(userId).getPassword())){
//            throw new RuntimeException("Invalid password");
//        }
        if(!loginRequest.getPassword().equals(userMapper.selectUserByUserId(userId).getPassword())){
            throw new RuntimeException("Invalid password");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, userId);
        Token token = jwtProvider.createToken(authentication);
        String accessToken = token.getAccessToken();
        String refreshToken = token.getRefreshToken();
        return new TokenResponse(accessToken,refreshToken);
    }

    private User toUser(String userId, String email){
        return User.builder()
                .userId(userId)
                .email(email)
                .role("U")
                .build();
    }
    private UserDetail toUserDetail(String userId, String name, String phone,String birth){
        return UserDetail.builder()
                .userId(userId)
                .name(name)
                .phone(phone)
                .birth(birth)
                .build();
    }
    private UserGeneral toUserGeneral(String userId,String password){
        return UserGeneral.builder()
                .userId(userId)
                .password(password)
                .build();
    }

//    private TokenResponse authorize(String userId, String email){
//        Authentication authentication = authenticationManagerBuilder.getObject()
//                .authenticate(new UsernamePasswordAuthenticationToken(email,userId));
//    }
}
