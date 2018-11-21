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
        user.setUid("1");
        user.setAge(1);
        user.setName("1");
        this.save(user);
        user = new User();
        user.setUid("2");
        user.setAge(2);
        user.setName("2");
        this.save(user);

        user = new User();
        user.setUid("3");
        user.setAge(3);
        user.setName("3");
        this.save(user);
        if (user.getUid().equalsIgnoreCase("3")) {
            throw new RuntimeException();
        }

        user = new User();
        user.setUid("4");
        user.setAge(5);
        user.setName("6");
        this.save(user);
    }
}
