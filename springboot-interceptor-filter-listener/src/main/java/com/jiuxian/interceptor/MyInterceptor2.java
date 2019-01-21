package com.jiuxian.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-21 14:04:00
 * Comment:
 */

public class MyInterceptor2 implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
        System.out.println(">>>>> MyInterceptor2 preHandle >>>>>>>>>>>>>>>>>>>>>>");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (long) request.getAttribute("startTime");
        System.out.println("MyInterceptor2 执行:" + (System.currentTimeMillis() - startTime));
        System.out.println(">>>>> MyInterceptor2 postHandle >>>>>>>>>>>>>>>>>>>>>>");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        request.removeAttribute("startTime");
        System.out.println(">>>>> MyInterceptor2 afterCompletion >>>>>>>>>>>>>>>>>>>>>>");
    }
}
