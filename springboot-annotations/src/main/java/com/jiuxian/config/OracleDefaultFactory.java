package com.jiuxian.config;

import com.jiuxian.condition.OracleCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-28 13:07:00
 * Comment:
 */
@ConditionalOnMissingBean(value = JdbcFactory.class, ignored = OracleDefaultFactory.class)
@Conditional(OracleCondition.class)
@Component
public class OracleDefaultFactory implements JdbcFactory {

    @Override
    public void create() {
        System.out.println("oracle ..create..");
    }
}
