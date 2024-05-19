package com.jeogi.jeogitrip.user.model.service;


import com.jeogi.jeogitrip.user.model.User;
import com.jeogi.jeogitrip.user.model.UserDetail;
import com.jeogi.jeogitrip.user.model.UserGeneral;
import com.jeogi.jeogitrip.user.model.UserRequest;

import java.util.List;

public interface UserService {
    List<User> listUser();
    UserDetail getUserDetail(String userId);

    User getUserByEmail(String email);
    int joinUser(UserRequest userRequest);
    int updateUser(UserRequest userRequest);
    int removeUser(String userId);
}
