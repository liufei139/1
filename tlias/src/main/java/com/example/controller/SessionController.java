package com.example.controller;

import com.example.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 会话跟踪技术：Cookie和Session
 */
@Slf4j
@RestController
public class SessionController {

    //设置Cookie
    @GetMapping("/c1")
    public Result cookie1(HttpServletResponse response){
        response.addCookie(new Cookie("login_username","example"));
        return Result.success();
    }

    //获取Cookie
    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("login_username")){
                System.out.println("login_username: "+cookie.getValue());
            }
        }
        return Result.success();
    }



    //往HttpSession中存储值
    @GetMapping("/s1")
    public Result session1(HttpSession session){
        log.info("HttpSession-s1:{}",session.hashCode());
        session.setAttribute("loginUser","Tom");
        return Result.success();
    }

    //从HttpSession中获取值
    @GetMapping("/s2")
    public Result session2(HttpServletRequest request){
        HttpSession session=request.getSession();
        log.info("HttpSession-s2:{}",session.hashCode());
        Object loginUser=session.getAttribute("loginUser");
        log.info("loginUser:{}",loginUser);
        return Result.success();
    }
}
