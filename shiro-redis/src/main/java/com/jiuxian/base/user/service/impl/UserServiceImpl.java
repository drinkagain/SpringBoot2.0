package com.jiuxian.base.user.service.impl;

import com.jiuxian.base.user.dao.UserDao;
import com.jiuxian.base.user.entity.User;
import com.jiuxian.base.user.service.UserService;
import com.jiuxian.core.service.impl.BaseServiceImpl;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private void setDao(UserDao dao) {
        this.dao = dao;
    }

    private UserDao getDao() {
        return ((UserDao) dao);
    }

    @Value("${shiro.hashAlgorithmName}")
    private String hashAlgorithmName;
    @Value("${shiro.hashIterations}")
    private int hashIterations;

    @Override
    public User findByAccount(String account) {
        return getDao().findByAccount(account);
    }

    private String getPassword(String password, String credentialsSalt) {
        SimpleHash hash = new SimpleHash(hashAlgorithmName, password, credentialsSalt, hashIterations);
        return hash.toHex();
    }

    @Override
    public User doSave(User bean) {
        bean.setCredentialsSalt(bean.getAccount() + UUID.randomUUID());
        bean.setPassword(getPassword(bean.getPassword(), bean.getCredentialsSalt()));
        return super.doSave(bean);
    }

    @Override
    public User doUpdate(User bean) {
        bean.setCredentialsSalt(bean.getAccount() + UUID.randomUUID());
        bean.setPassword(getPassword(bean.getPassword(), bean.getCredentialsSalt()));
        return super.doUpdate(bean);
    }
}
