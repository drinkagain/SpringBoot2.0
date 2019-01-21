package com.jiuxian.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-21 16:48:00
 * Comment:
 */
@WebListener
public class MyListener2 implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("MyListener2");
    }
}
