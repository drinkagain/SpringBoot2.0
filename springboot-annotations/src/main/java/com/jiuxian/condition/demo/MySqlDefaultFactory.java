package com.jiuxian.condition.demo;

import com.jiuxian.condition.MySqlCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-28 13:08:00
 * Comment:
 */
@ConditionalOnMissingBean(value = JdbcFactory.class, ignored = MySqlDefaultFactory.class)
@Conditional(MySqlCondition.class)
@Component
public class MySqlDefaultFactory implements JdbcFactory {

    @Override
    public void create() {
        System.out.println("Default MySql create ..");
    }

}
