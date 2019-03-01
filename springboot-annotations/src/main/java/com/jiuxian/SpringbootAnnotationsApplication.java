package com.jiuxian;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@SpringBootApplication
//@EnableScheduling
//@EnableAsync
//@EnableAspectJAutoProxy
//@EnableTransactionManagement
//@EnableCaching
//@EnableConfigurationProperties
@Configuration
public class SpringbootAnnotationsApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringbootAnnotationsApplication.class, args);

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime.getYear());
        System.out.println(dateTime.getMonthValue());
    }

}

