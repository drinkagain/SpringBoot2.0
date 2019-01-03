package com.jiuxian.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.jiuxian.*.mapper")
public class MybatisConfig {
}
