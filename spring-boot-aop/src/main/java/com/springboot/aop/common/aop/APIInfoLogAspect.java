package com.springboot.aop.common.aop;

import com.alibaba.fastjson.JSON;
import com.springboot.aop.common.annotation.RequestLimit;
import com.springboot.aop.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Aspect
@Component
public class APIInfoLogAspect {
    private static final Logger logger = LogManager.getLogger(APIInfoLogAspect.class);

    //    @Pointcut("execution(* com.springboot.aop.controller.*.*(..))")
//    @Pointcut("within(com.springboot.aop.controller..*)")
//    @Pointcut("execution(public * *(..))")
//    @Pointcut("execution(* save*(..))")
//    @Pointcut("execution(* com.springboot.aop.service.UserService.*(..))")
//    @Pointcut("execution(* com.springboot.aop.service..*(..))")
//    @Pointcut("within(com.springboot.aop.service..*)")
//    @Pointcut("this(com.springboot.aop.service.LoginService)")
//    @Pointcut("target(com.springboot.aop.service.LoginService)")
//    @Pointcut("bean(loginServiceImpl)")
//    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    @Pointcut("@annotation(requestMapping)")
    public void pointcut(RequestMapping requestMapping) {
    }

    @Before("pointcut(requestMapping)")
    public void before(JoinPoint joinPoint, RequestMapping requestMapping) {
//    public void around(JoinPoint joinPoint, RequestLimit requestLimit) throws IOException {
        Object[] args = joinPoint.getArgs();
        String fullClassName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //请求信息
        logger.info("Request URI:{}, API:{}, Request Parameters:{}", request.getRequestURI(), fullClassName + "." + methodName, JSON.toJSONString(args));
    }

    @AfterReturning(returning = "response", pointcut = "pointcut(requestMapping)")
    public void logReturnParameters(Object response, RequestMapping requestMapping) {
        if (response != null) {
            //响应信息
            logger.info("Response Parameters:{}", JSON.toJSONString(response));
        }
    }

}
