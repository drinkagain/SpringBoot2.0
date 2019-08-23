package com.jiuxian.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jiuxian.BaseOrmMybatisApplicationTests;
import com.jiuxian.core.entity.User;
import com.jiuxian.core.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>query().lambda().eq(User::getAge, 30);
        userService.list(wrapper);

        LambdaQueryWrapper<User> wrapper1 = new QueryWrapper<User>().lambda().eq(User::getAge, 30);
        userService.list(wrapper1);

        userService.lambdaQuery().eq(User::getAge, 30).list();

        QueryWrapper<User> age = Wrappers.<User>query().ge("age", 30);

        userService.list(age);

    }


    @Test
    public void testLambdaSelect() {
        LambdaQueryWrapper<User> id = Wrappers.<User>query().lambda().eq(User::getId, "1");
        id.eq(User::getId, "2");
        for (int i = 0; i < 100000; i++) {
            List<User> list = userService.list(id);
            System.out.println(list);
        }
    }

}
