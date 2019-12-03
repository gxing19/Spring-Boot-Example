package com.springboot.aop.common.aop;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

//@Aspect
//@Component
public class APIInfoLogAspect {
    private static final Logger logger = LogManager.getLogger(APIInfoLogAspect.class);

    @Pointcut("execution(* com.springboot.demo.controller.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void logRequestParameters(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String fullClassName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //请求信息
        logger.info("Request URI:{}, API:{}, Method Parameters:{}", request.getRequestURI(), fullClassName + "." + methodName, JSON.toJSONString(args));
    }

    @AfterReturning(returning = "response", pointcut = "pointcut()")
    public void logReturnParameters(Object response) {
        if (response != null) {
            //响应信息
            logger.info("Response Parameters:{}", JSON.toJSONString(response));
        }
    }
}
