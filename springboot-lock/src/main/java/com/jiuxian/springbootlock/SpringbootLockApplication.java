package com.jiuxian.springbootlock;

import com.jiuxian.springbootlock.common.LockService;
import com.jiuxian.springbootlock.lock.RedisLockServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author liuzejun
 */
@SpringBootApplication
public class SpringbootLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLockApplication.class, args);
    }

    @Resource
    private RedisTemplate<String, Long> redisTemplate;

    @Bean
    public LockService lockService() {
        return new RedisLockServiceImpl(redisTemplate);
    }

}
