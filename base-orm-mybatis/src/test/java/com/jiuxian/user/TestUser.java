package com.jiuxian.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    }


    @Test
    public void testLambdaSelect() {
        Logger logger = LoggerFactory.getLogger(TestUser.class);
        LambdaQueryWrapper<User> id = Wrappers.<User>query().lambda().eq(User::getUid, "1");
        id.eq(User::getUid, "2");
        for (int i = 0; i < 100000; i++) {
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");
            logger.info(">>>>>>>>>>>ASDFGHJKLKJHGFVDCVBNM>>>>>>>>>>>");

            List<User> list = userService.list(id);
            System.out.println(list);
        }
    }

}
