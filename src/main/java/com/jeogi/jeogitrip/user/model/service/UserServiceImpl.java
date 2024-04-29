package com.jeogi.jeogitrip.user.model.service;

import com.jeogi.jeogitrip.user.model.User;
import com.jeogi.jeogitrip.user.model.UserDetail;
import com.jeogi.jeogitrip.user.model.UserGeneral;
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

    @Transactional
    @Override
    public int joinUser(User user, UserGeneral userGeneral, UserDetail userDetail) {
        try {
            int userInserted = userMapper.insertUser(user);
            int userDetailInserted = userMapper.insertUserDetail(userDetail);
            int userGeneralInserted = userMapper.insertUserGeneral(userGeneral);

            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return userInserted+userDetailInserted+userGeneralInserted;
        }catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("회원 등록 중 오류가 발생했습니다.", e);
        }
    }

    @Transactional
    @Override
    public int updateUser(User user, UserGeneral userGeneral, UserDetail userDetail) {
        try{
            int userUpdated = userMapper.updateUser(user);
            int userDetailUpdated = userMapper.updateUserDetail(userDetail);
            int userGeneralUpdated = userMapper.updateUserGeneral(userGeneral);

            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return userUpdated+userDetailUpdated+userGeneralUpdated;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("회원 정보 업데이트 중 오류 발생", e);
        }

    }

    @Transactional
    @Override
    public int removeUser(String userId) {
        try{
            userMapper.deleteUserDetail(userId);
            userMapper.deleteUserGeneral(userId);

            User user = new User();
            user.setUserId(userId);
            user.setRole('X');
            userMapper.updateUser(user);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("회원 삭제 중 오류 발생", e);
        }
        return 0;
    }
}
