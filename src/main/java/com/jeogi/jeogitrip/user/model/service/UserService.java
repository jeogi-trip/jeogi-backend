package com.jeogi.jeogitrip.user.model.service;


import com.jeogi.jeogitrip.user.model.User;
import com.jeogi.jeogitrip.user.model.UserDetail;
import com.jeogi.jeogitrip.user.model.UserGeneral;

import java.util.List;

public interface UserService {
    List<User> listUser();
    UserDetail getUserDetail(String userId);

    int joinUser(User user, UserGeneral userGeneral, UserDetail userDetail);
    int updateUser(User user, UserGeneral userGeneral, UserDetail userDetail);
    int removeUser(String userId);
}
