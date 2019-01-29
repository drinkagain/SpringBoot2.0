package com.jiuxian.config;

import org.springframework.stereotype.Component;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-28 13:10:00
 * Comment:
 */

//@Component
public class MysqlFactory implements JdbcFactory {

    @Override
    public void create() {
        System.out.println("Define 。。 create");
    }
}
