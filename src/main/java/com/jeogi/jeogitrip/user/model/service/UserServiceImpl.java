package com.jeogi.jeogitrip.user.model.service;

import com.jeogi.jeogitrip.user.model.User;
import com.jeogi.jeogitrip.user.model.UserDetail;
import com.jeogi.jeogitrip.user.model.UserGeneral;
import com.jeogi.jeogitrip.user.model.UserRequest;
import com.jeogi.jeogitrip.user.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements  UserService{

    private final UserMapper userMapper;

    @Override
    public List<User> listUser() {
        return userMapper.selectUser();
    }

    @Override
    public UserDetail getUserDetail(String userId) {
        return userMapper.selectUserDetail(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

    @Transactional
    @Override
    public int joinUser(UserRequest userRequest) {
        try {
            User user = User.builder()
                    .userId(userRequest.getUserId()).email(userRequest.getEmail()).build();

            UserDetail userDetail = UserDetail
                    .builder()
                    .userId(userRequest.getUserId())
                    .name(userRequest.getName())
                    .birth(userRequest.getBirth())
                    .phone(userRequest.getPhone())
                    .build();
            UserGeneral userGeneral = UserGeneral
                    .builder()
                    .userId(userDetail.getUserId())
                    .password(userRequest.getPassword())
                    .build();

            int userInserted = userMapper.insertUser(user);
            int userDetailInserted = userMapper.insertUserDetail(userDetail);
            int userGeneralInserted = userMapper.insertUserGeneral(userGeneral);

            return userInserted+userDetailInserted+userGeneralInserted;
        }catch (Exception e) {
            throw new RuntimeException("회원 등록 중 오류가 발생했습니다.", e);
        }
    }

    @Transactional
    @Override
    public int updateUser(UserRequest userRequest) {

        try{
            User user = User.builder()
                .userId(userRequest.getUserId()).email(userRequest.getEmail()).build();

            UserDetail userDetail = UserDetail
                    .builder()
                    .userId(userRequest.getUserId())
                    .name(userRequest.getName())
                    .birth(userRequest.getBirth())
                    .phone(userRequest.getPhone())
                    .build();
            UserGeneral userGeneral = UserGeneral
                    .builder()
                    .userId(userDetail.getUserId())
                    .password(userRequest.getPassword())
                    .build();

            int userUpdatedCnt = 0;

            if(user.getEmail() != null){
                userUpdatedCnt += userMapper.updateUser(user);
            }
            if(userDetail.getName() != null || userDetail.getBirth() != null || userDetail.getPhone() != null){
               userUpdatedCnt += userMapper.updateUserDetail(userDetail);
            }
            if(userGeneral.getPassword() != null){
                userUpdatedCnt += userMapper.updateUserGeneral(userGeneral);
            }

            return userUpdatedCnt;
        }catch (Exception e){
            throw new RuntimeException("회원 정보 업데이트 중 오류 발생", e);
        }

    }

    @Transactional
    @Override
    public int removeUser(String userId) {
        try{
            userMapper.deleteUserDetail(userId);
            userMapper.deleteUserGeneral(userId);

            User user = User.builder()
                    .userId(userId)
                    .role("X").build();
            userMapper.updateUser(user);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("회원 삭제 중 오류 발생", e);
        }
        return 0;
    }
}
