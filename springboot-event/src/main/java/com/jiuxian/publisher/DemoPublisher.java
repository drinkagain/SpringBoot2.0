package com.jiuxian.publisher;

import com.jiuxian.event.DemoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

/**
 * Author: jiuxian
 * Date: 2019-03-04 23:40:00
 * Comment:
 */

@Component
public class DemoPublisher {

    private final ApplicationContext applicationContext;

    @Autowired
    public DemoPublisher(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void publish(long id, String message) {
        applicationContext.publishEvent(new DemoEvent(this, id, message));
    }

}
