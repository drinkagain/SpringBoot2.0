package com.jiuxian.core.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
public class TransactionAdviceConfig {

    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.jiuxian.core.service.*.*(..))";
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public TransactionAdviceConfig(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Bean
    public TransactionInterceptor txAdvice() {

        DefaultTransactionAttribute required = new DefaultTransactionAttribute();
        required.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        DefaultTransactionAttribute readonly = new DefaultTransactionAttribute();
        readonly.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        readonly.setReadOnly(true);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("add*", required);
        source.addTransactionalMethod("save*", required);
        source.addTransactionalMethod("delete*", required);
        source.addTransactionalMethod("update*", required);
        source.addTransactionalMethod("exec*", required);
        source.addTransactionalMethod("set*", required);
        source.addTransactionalMethod("do*", required);

        source.addTransactionalMethod("get*", readonly);
        source.addTransactionalMethod("query*", readonly);
        source.addTransactionalMethod("find*", readonly);
        source.addTransactionalMethod("list*", readonly);
        source.addTransactionalMethod("count*", readonly);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor(TransactionInterceptor txAdvice) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }
}
