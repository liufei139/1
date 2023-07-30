package com.example.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.Utils.JwtUtils;
import com.example.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        //获取URL
        String url = request.getRequestURL().toString();
        log.info("请求的url:{}",url);
        //判断是否包含/login，如果有则放行
        if(url.contains("login")){
            log.info("登陆操作，放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //获取请求头中的令牌Token
        String jwt = request.getHeader("token");
        //判断令牌是否存在，如果不存在，返回错误结果
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录信息");
            Result error=Result.error("NOT_LOGIN");
            //对象->JSON
            String notLogin = JSONObject.toJSONString(error);
            //向浏览器相应
            response.getWriter().write(notLogin);
            return;
        }
        //解析token，如果解析失败，返回错误结果
        try{
            JwtUtils.parseJWT(jwt);
        }catch (Exception e){
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误信息");
            Result error=Result.error("NOT_LOGIN");
            //对象->JSON
            String notLogin = JSONObject.toJSONString(error);
            //向浏览器相应
            response.getWriter().write(notLogin);
            return;
        }
        //放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
