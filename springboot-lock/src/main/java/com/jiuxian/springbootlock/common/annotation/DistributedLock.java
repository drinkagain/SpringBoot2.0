package com.jiuxian.springbootlock.common.annotation;

import java.lang.annotation.*;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-27 12:24:50
 * *
 * @description: 分布式锁注解
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DistributedLock {

    String parameterKey() default "";

    int parameterKeyIndex() default -1;

    String methodKey() default "";
}
