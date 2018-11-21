package com.jiuxian.core.service;

import com.jiuxian.core.entity.User;

public interface UserService extends BaseService<User> {
    void doSaveBatch();
}
