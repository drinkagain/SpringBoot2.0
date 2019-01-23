package com.jiuxian.service;

import com.jiuxian.config.JiuxianProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-23 16:56:00
 * Comment:
 */


@Service
public class UserService {

    @Resource
    private JiuxianProperties properties;

    public void getProperties() {
        System.out.println(properties);
    }

}
