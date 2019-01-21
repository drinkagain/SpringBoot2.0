package com.jiuxian.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-21 16:40:00
 * Comment:
 */


public class MyListener1 implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("MyListener1 ... ");
    }
}
