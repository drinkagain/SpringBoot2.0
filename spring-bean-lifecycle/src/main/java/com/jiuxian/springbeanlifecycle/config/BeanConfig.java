package com.jiuxian.springbeanlifecycle.config;

import com.jiuxian.springbeanlifecycle.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-26 10:45:19
 * *
 * @description:
 **/
@Configuration
public class BeanConfig {

    @Bean(initMethod = "myInit", destroyMethod = "myDestroy")
    public User user() {
        return new User();
    }

}
