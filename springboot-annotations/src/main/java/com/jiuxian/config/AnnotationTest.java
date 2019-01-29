package com.jiuxian.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-28 11:22:00
 * Comment:
 */


//@ConditionalOnMissingBean(Test2.class)
@EnableConfigurationProperties
//@ConditionalOnBean(Test3.class)
@ConditionalOnWebApplication
//@ConditionalOnClass({ Test1.class })
@ConditionalOnMissingClass({ "Test3" })
@Configuration
public class AnnotationTest {

    public AnnotationTest() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
