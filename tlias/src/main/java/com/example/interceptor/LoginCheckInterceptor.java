package com.example.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.Utils.JwtUtils;
import com.example.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 根据Interceptor进行登录校验
 */
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override//目标资源方法运行前执行，true放行，false拦截
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");

        //获取URL
        String url = request.getRequestURL().toString();
        log.info("请求的url:{}",url);
        //判断是否包含/login，如果有则放行
        if(url.contains("login")){
            log.info("登陆操作，放行");
            return true;
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
            return false;
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
            return false;
        }
        //放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override//目标资源运行之后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
        //HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override//视图渲染完毕之后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
        //HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
