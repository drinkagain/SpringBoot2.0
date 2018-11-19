package com.jiuxian.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.ISqlSegment;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxian.core.entity.User;
import com.jiuxian.core.mapper.UserMapper;
import com.jiuxian.core.service.BaseService;

public class BaseServiceImpl extends ServiceImpl<UserMapper, User> implements BaseService {

    public void test(){
    }
}
