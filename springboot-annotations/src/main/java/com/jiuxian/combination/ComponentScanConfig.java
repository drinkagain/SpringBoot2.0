package com.jiuxian.combination;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-29 10:12:00
 * Comment:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@ComponentScan
public @interface ComponentScanConfig {
    //这个必须写，覆盖@ComponentScan的注解value的值
    String[] value() default {};
}
