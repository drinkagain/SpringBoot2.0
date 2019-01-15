package com.jiuxian.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Author: jiuxian
 * Date: 2019-01-14 23:26:00
 * Comment:  自定义的一些配置
 */


//@Configuration
//@EnableScheduling
public class ScheduledConfig2 implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
        taskRegistrar.addTriggerTask(
                () -> myTask().work(),
                new CustomTrigger()
        );
    }


    @Bean
    public MyTask myTask() {
        return new MyTask();
    }


    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(20);
    }
}

class CustomTrigger implements Trigger {

    @Override
    public Date nextExecutionTime(TriggerContext triggerContext) {
        Date date = triggerContext.lastCompletionTime();
        return new Date(date == null ? new Date().getTime() : date.getTime() + 5000);
    }
}

class MyTask {

    public void work() {
        System.out.println("work");
    }
}
