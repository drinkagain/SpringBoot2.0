package com.jiuxian.base.user.service;

import com.jiuxian.base.user.entity.User;
import com.jiuxian.service.BaseService;

public interface UserService extends BaseService<User> {
    User findByAccount(String account);
}
