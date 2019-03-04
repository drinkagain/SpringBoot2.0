package com.jiuxian.event;

import org.springframework.context.ApplicationEvent;

/**
 * Author: jiuxian
 * Date: 2019-03-04 23:35:00
 * Comment:
 */

public class DemoEvent extends ApplicationEvent {
    private Long id;
    private String message;

    public DemoEvent(Object source) {
        super(source);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
