package com.jiuxian.core.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

@Configuration
@MapperScan("com.jiuxian.**.mapper")
public class MybatisConfig implements MetaObjectHandler {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        dynamicTableNameParser.setTableNameHandlerMap(new HashMap<String, ITableNameHandler>() {{
            put("user", (metaObject, sql, tableName) -> {
                System.out.println(tableName);
                return tableName+"1";
            });
        }});
        paginationInterceptor.setSqlParserList(Collections.singletonList(dynamicTableNameParser));
        return paginationInterceptor;
    }

    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    @Bean
    public ITableNameHandler iTableNameHandler() {
        return (metaObject, sql, tableName) -> {
            System.out.println(tableName);
            return null;
        };
    }


    /**
     * 公用字段配置
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("creatTime")) {
            metaObject.setValue("creatTime", new Date());
        }
        if (metaObject.hasSetter("updateTime")) {
            metaObject.setValue("updateTime", new Date());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("updateTime")) {
            metaObject.setValue("updateTime", new Date());
        }
    }
}
