package com.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切入点类，可以将多个共同的切入点表达式抽取出来放到该类中
 */
@Slf4j
@Component
//@Aspect
public class PointCut {

    /**
     * 此注解的作用为将多个通知的切入点表达式抽取到一个空方法中
     */
    @Pointcut("execution(* com.example.service.*.*(..))")

    /**
     * 使用该自定义注解，只需在需要进行切入的方法上面加上@MyLog
     */
    //@Pointcut("@annotation(com.example.anno.MyLog)")
    public void pointCut(){}

    //@Around("execution(* com.example.service.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取目标类名
        String className=joinPoint.getTarget().getClass().getName();
        //获取目标方法签名
        Signature signature=joinPoint.getSignature();
        //获取目标方法名
        String methodName=joinPoint.getSignature().getName();
        //获取目标方法运行参数
        Object[] args=joinPoint.getArgs();
        //执行原始方法，获取返回值（环绕通知）
        Object res=joinPoint.proceed();

        return res;
    }

    //@Before("execution(* com.example.service.*.*(..))")
    public void before(JoinPoint joinPoint){
        //获取目标类名
        String className=joinPoint.getTarget().getClass().getName();
        //获取目标方法签名
        Signature signature=joinPoint.getSignature();
        //获取目标方法名
        String methodName=joinPoint.getSignature().getName();
        //获取目标方法运行参数
        Object[] args=joinPoint.getArgs();
    }
}
