package com.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * AOP学习测试类
 */
@Slf4j
@Component
//@Aspect//当前类为AOP类
public class TimeAspect {

    @Around("com.example.aop.PointCut.pointCut()")//切入点表达式
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();

        //调用原始方法执行
        Object proceed = joinPoint.proceed();

        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature()+"方法执行耗时：{}ms",end-begin);

        return proceed;

    }
    @Before("com.example.aop.PointCut.pointCut()")
    public void before(){
        log.info("before");
    }
    @After("com.example.aop.PointCut.pointCut()")
    public void after(){
        log.info("after");
    }
    @AfterReturning("com.example.aop.PointCut.pointCut()")
    public void afterReturning(){
        log.info("AfterReturning");
    }
    @AfterThrowing("com.example.aop.PointCut.pointCut()")
    public void afterThrowing(){
        log.info("AfterThrowing");
    }
}
