package com.jiuxian.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-28 14:24:00
 * Comment:
 */


public class MySqlCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return "mysql".equals(context.getEnvironment().getProperty("db-type"));
    }
}
