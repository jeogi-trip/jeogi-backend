package com.jeogi.jeogitrip.user.model.mapper;

import com.jeogi.jeogitrip.user.model.User;
import com.jeogi.jeogitrip.user.model.UserDetail;
import com.jeogi.jeogitrip.user.model.UserGeneral;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    String selectEmailByUserId(String userId);
    List<User> selectUser();
    UserDetail selectUserDetail(String userId);
    User selectUserByEmail(String email);
    UserGeneral selectUserByUserId(String userId);

    int insertUser(User user);
    int insertUserGeneral(UserGeneral userGeneral);
    int insertUserDetail(UserDetail userDetail);

    int updateUser(User user);
    int updateUserGeneral(UserGeneral userGeneral);
    int updateUserDetail(UserDetail userDetail);

    int deleteUserDetail(String userId);
    int deleteUserGeneral(String userId);
}
