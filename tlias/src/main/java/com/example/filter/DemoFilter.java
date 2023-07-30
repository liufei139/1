package com.example.filter;

import com.example.Utils.JwtUtils;
import io.jsonwebtoken.Claims;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {

    //初始化方法，在程序启动时调用一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
        Filter.super.init(filterConfig);
    }

    //拦截到请求之后调用，调用多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("DemoFilter 拦截到请求");
        System.out.println("放行前");
        servletResponse.getContentType();
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("放行后");
    }

    //销毁方法，在程序结束时调用一次
    @Override
    public void destroy() {
        System.out.println("destroy");
        Filter.super.destroy();
    }
}
