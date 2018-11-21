package com.jiuxian.user;

import com.jiuxian.BaseOrmMybatisApplicationTests;
import com.jiuxian.core.entity.User;
import com.jiuxian.core.service.UserService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class TestUser extends BaseOrmMybatisApplicationTests {

    @Resource
    private UserService userService;


    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userService.list();
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }


    @Test
    public void doSaveBatch() {
        userService.doSaveBatch();
    }

}
