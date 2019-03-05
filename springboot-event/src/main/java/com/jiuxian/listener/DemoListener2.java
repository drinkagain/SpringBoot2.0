package com.jiuxian.listener;

import com.jiuxian.event.DemoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Author: LIU ZEJUN
 * Date: 2019-03-05 10:33:00
 * Comment:
 */

@Component
public class DemoListener2 {

    @EventListener
    public void onApplicationEvent(DemoEvent demoEvent) {
        System.out.println(">>>>>>>>>DemoListener2>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("收到了：" + demoEvent.getSource() + "消息;时间：" + demoEvent.getTimestamp());
        System.out.println("消息：" + demoEvent.getId() + ":" + demoEvent.getMessage());
    }
}
