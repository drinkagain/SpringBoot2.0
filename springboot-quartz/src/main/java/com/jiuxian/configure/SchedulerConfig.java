package com.jiuxian.configure;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * @author liuzejun
 * @Date 16:46 2018/3/2
 */
@Configuration
public class SchedulerConfig {

    @Resource(name = "dataSource")
    private DataSource dataSource;

    @Bean(name = "SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setStartupDelay(5);
        bean.setAutoStartup(true);
        bean.setApplicationContextSchedulerContextKey("applicationContext");
        bean.setConfigLocation(new ClassPathResource("/quartz.properties"));
        return bean;
    }

    /*
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }
}
