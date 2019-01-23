package com.jiuxian;

import com.jiuxian.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootYmlApplicationTests {

    @Resource
    private UserService userService;

    @Test
    public void propertiesTest() {
        userService.getProperties();
    }
}

