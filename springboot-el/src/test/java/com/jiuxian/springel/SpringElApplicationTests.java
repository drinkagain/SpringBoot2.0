package com.jiuxian.springel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringElApplicationTests {

    @Resource
    private ElConfig elConfig;

    @Test
    public void ElConfigTest() {
        elConfig.getSecret();
    }

}

