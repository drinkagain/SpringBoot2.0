package com.jiuxian;

import com.jiuxian.combination.CombinationAnnotationTestService;
import com.jiuxian.condition.demo.JdbcFactory;
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

    /**
     * 条件注解测试
     */
    @Test
    public void conditionOnMissBean() {
        jdbcFactory.create();
    }

    @Resource
    private CombinationAnnotationTestService combinationAnnotationTestService;

    /**
     * 组合注解测试
     */
    @Test
    public void combinationTest() {
        combinationAnnotationTestService.doSth();
    }

}

