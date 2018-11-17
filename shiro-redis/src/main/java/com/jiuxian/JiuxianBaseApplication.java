package com.jiuxian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories
//@EnableRedisHttpSession
@SpringBootApplication
public class JiuxianBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiuxianBaseApplication.class, args);
    }
}
