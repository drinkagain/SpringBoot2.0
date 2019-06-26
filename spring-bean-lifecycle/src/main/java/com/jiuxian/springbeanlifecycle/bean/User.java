package com.jiuxian.springbeanlifecycle.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.*;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-26 10:37:37
 * *
 * @description: 用户
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends InstantiationAwareBeanPostProcessorAdapter
        implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean,
        BeanPostProcessor, BeanFactoryPostProcessor {

    private String name;

    private String account;

    private String password;

    private BeanFactory beanFactory;

    private String beanName;

    public User() {
        System.out.println("构造方法...");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;

        System.out.println("[BeanFactoryAware]接口调用 setBeanFactory(BeanFactory beanFactory)。。。");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;

        System.out.println("[BeanNameAware]接口调用 setBeanName(String name)。。。");
    }

    @Override
    public void destroy() {

        System.out.println("[DisposableBean]接口调用 destroy()。。。");
    }

    @Override
    public void afterPropertiesSet() {

        System.out.println("[InitializingBean]接口调用 afterPropertiesSet()。。。");
    }

    public void myInit() {

        System.out.println("自定义initMethod 。。。");
    }

    public void myDestroy() {

        System.out.println("自定义destroyMethod 。。。");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[BeanPostProcessor]postProcessBeforeInitialization(" + beanName + ", " + beanName + " ) 对属性进行更改");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[BeanPostProcessor]postProcessAfterInitialization(" + beanName + ", " + beanName + ") 对属性进行更改");
        return bean;
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        System.out.println("[BeanFactoryPostProcessor]工厂后处理器接口postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)");
        BeanDefinition user = beanFactory.getBeanDefinition("user");
        user.getPropertyValues().addPropertyValue("account", "zhangsan");
    }
}
