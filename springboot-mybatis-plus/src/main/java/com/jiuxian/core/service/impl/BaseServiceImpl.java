package com.jiuxian.core.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuxian.core.entity.BaseEntity;
import com.jiuxian.core.service.BaseService;

public class BaseServiceImpl<M extends BaseMapper, T extends BaseEntity> extends ServiceImpl<BaseMapper<T>, T> implements BaseService<T> {

}
