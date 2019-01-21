package com.jiuxian.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: jiuxian
 * Date: 2019-01-17 22:03:00
 * Comment:
 */
public class MyInterceptor1 extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
        System.out.println(">>>>> MyInterceptor1 preHandle >>>>>>>>>>>>>>>>>>>>>>");
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (long) request.getAttribute("startTime");
        System.out.println("MyInterceptor1 执行:" + (System.currentTimeMillis() - startTime));
        System.out.println(">>>>> MyInterceptor1 postHandle >>>>>>>>>>>>>>>>>>>>>>");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        request.removeAttribute("startTime");
        System.out.println(">>>>> MyInterceptor1 afterCompletion >>>>>>>>>>>>>>>>>>>>>>");
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
        System.out.println(">>>>> MyInterceptor1 afterConcurrentHandlingStarted >>>>>>>>>>>>>>>>>>>>>>");
    }
}
