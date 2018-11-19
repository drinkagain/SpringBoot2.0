package com.jiuxian.user;

import com.jiuxian.BaseOrmMybatisApplicationTests;
import com.jiuxian.core.entity.User;
import com.jiuxian.core.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class TestUser extends BaseOrmMybatisApplicationTests {

    @Resource
    private UserMapper userMapper;


    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}
