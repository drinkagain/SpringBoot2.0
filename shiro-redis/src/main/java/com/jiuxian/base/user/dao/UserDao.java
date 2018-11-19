package com.jiuxian.base.user.dao;

import com.jiuxian.base.user.entity.User;
import com.jiuxian.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<User> {
    User findByAccount(String account);
}
