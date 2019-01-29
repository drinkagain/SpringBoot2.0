package com.jiuxian;

import com.jiuxian.combination.CombinationAnnotationTestService;
import com.jiuxian.config.JdbcFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAnnotationsApplicationTests {


    @Resource
    private JdbcFactory jdbcFactory;

    @Test
    public void conditionOnMissBean() {
        jdbcFactory.create();
    }

    @Resource
    private CombinationAnnotationTestService combinationAnnotationTestService;

    @Test
    public void combinationTest() {
        combinationAnnotationTestService.doSth();
    }

}

