package com.jiuxian.import_.annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * Author: LIU ZEJUN
 * Date: 2019-02-26 17:13:00
 * Comment:
 */

//@Configuration
public class AConfiguration {
    public AConfiguration() {
        System.out.println(this.getClass().getSimpleName());
    }

    @Bean
    public BConfiguration bConfiguration() {
        return new BConfiguration();
    }

    public void foo(){
        System.out.println("A foo..");
    }
}
