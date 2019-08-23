package com.jiuxian.core.service.impl;

import com.jiuxian.core.entity.User;
import com.jiuxian.core.mapper.UserMapper;
import com.jiuxian.core.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
    @Override
    public void doSaveBatch() {
        User user = new User();
        user.setName("6");
        this.save(user);
    }
}
