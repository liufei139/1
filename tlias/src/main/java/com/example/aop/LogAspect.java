package com.example.aop;

import com.alibaba.fastjson.JSONObject;
import com.example.Utils.JwtUtils;
import com.example.mapper.OperateLogMapper;
import com.example.pojo.OperateLog;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * AOP，添加对增删改的日志到数据库表中
 */
@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Around("@annotation(com.example.anno.Log)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //操作人ID--从令牌中获取
        //获取请求头中的JWT令牌，解析令牌
        String jwt = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");
        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        //操作类名
        String className = joinPoint.getTarget().getClass().getName();
        //操作方法名
        String methodName = joinPoint.getSignature().getName();
        //操作方法参数
        Object[] args=joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        Long begin=System.currentTimeMillis();
        //执行原始方法
        Object result = joinPoint.proceed();
        Long end=System.currentTimeMillis();

        //方法返回值
        String returnValue = JSONObject.toJSONString(result);
        //操作耗时
        Long costTime=end-begin;

        //创建实体类封装数据
        OperateLog operateLog=new OperateLog();

        operateLog.setOperateUser(operateUser);
        operateLog.setOperateTime(operateTime);
        operateLog.setClassName(className);
        operateLog.setMethodName(methodName);
        operateLog.setMethodParams(methodParams);
        operateLog.setReturnValue(returnValue);
        operateLog.setCostTime(costTime);

        operateLogMapper.insert(operateLog);

        log.info("operateLog:{}",operateLog);

        return result;
    }
}
