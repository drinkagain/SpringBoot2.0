package com.jiuxian;

import com.jiuxian.config.JiuxianProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JiuxianProperties.class)
public class SpringbootYmlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootYmlApplication.class, args);
    }

}