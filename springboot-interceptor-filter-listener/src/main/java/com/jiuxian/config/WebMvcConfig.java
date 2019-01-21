package com.jiuxian.config;

import com.jiuxian.filter.MyFilter1;
import com.jiuxian.interceptor.MyInterceptor1;
import com.jiuxian.interceptor.MyInterceptor2;
import com.jiuxian.listener.MyListener1;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author: jiuxian
 * Date: 2019-01-17 22:17:00
 * Comment:
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**");
        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
    }

    @Bean
    public FilterRegistrationBean<MyFilter1> filterRegistrationBean() {
        FilterRegistrationBean<MyFilter1> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setFilter(new MyFilter1());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addInitParameter("initParam", "initOk");
        return filterRegistrationBean;
    }

    @Bean
    public MyFilter1 myFilter() {
        return new MyFilter1();
    }


    @Bean
    public ServletListenerRegistrationBean<MyListener1> registrationBean() {
        ServletListenerRegistrationBean<MyListener1> servletListenerRegistrationBean
                = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(new MyListener1());
        return servletListenerRegistrationBean;
    }

    @Bean
    public MyListener1 myListener1() {
        return new MyListener1();
    }
}
