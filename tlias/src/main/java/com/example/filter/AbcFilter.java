package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
//@WebFilter(urlPatterns = "/*")
public class AbcFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("AbcFilter 拦截到请求");
        System.out.println("放行前");

        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("放行后");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
