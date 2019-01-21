package com.jiuxian.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-21 16:06:00
 * Comment:
 */


public class MyFilter1 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(filterConfig.getInitParameter("initParam"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter1 >>>>>>>>>>>");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
