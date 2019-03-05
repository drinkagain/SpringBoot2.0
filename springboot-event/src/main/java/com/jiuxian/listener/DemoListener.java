package com.jiuxian.listener;

import com.jiuxian.event.DemoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Author: jiuxian
 * Date: 2019-03-04 23:38:00
 * Comment:
 */

@Component
public class DemoListener implements ApplicationListener<DemoEvent> {

    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
        System.out.println(">>>>>>>>>DemoListener>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("收到了：" + demoEvent.getSource() + "消息;时间：" + demoEvent.getTimestamp());
        System.out.println("消息：" + demoEvent.getId() + ":" + demoEvent.getMessage());
    }
}
