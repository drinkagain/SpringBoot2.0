package com.jiuxian.service.impl;

import com.jiuxian.annotation.Log;
import com.jiuxian.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-22 11:45:00
 * Comment:
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public String save(String user) {
        System.out.println("保存用户信息");
        if ("a".equals(user)) {
            throw new RuntimeException();
        }
        return user;
    }

    @Log(value = "test")
    @Override
    public void testAnnotationAop() {
        System.out.println("testAnnotationAop");
    }

    @Override
    public void testIntroduction() {
        System.out.println("do testIntroduction");
    }
}
