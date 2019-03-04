package com.jiuxian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.time.LocalDateTime;

@SpringBootApplication
//@EnableScheduling
//@EnableAsync
//@EnableAspectJAutoProxy
//@EnableTransactionManagement
//@EnableCaching
//@EnableConfigurationProperties
public class SpringbootAnnotationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAnnotationsApplication.class, args);

    }

}

