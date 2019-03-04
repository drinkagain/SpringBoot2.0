package com.jiuxian.event;

import org.springframework.context.ApplicationListener;

/**
 * Author: jiuxian
 * Date: 2019-03-04 23:38:00
 * Comment:
 */


public class DemoListener implements ApplicationListener<DemoEvent> {

    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
        System.out.println("收到了：" + demoEvent.getSource() + ";" + demoEvent.getTimestamp());
        System.out.println("收到了消息》》" + demoEvent.getId() + ":" + demoEvent.getMessage());
    }
}
