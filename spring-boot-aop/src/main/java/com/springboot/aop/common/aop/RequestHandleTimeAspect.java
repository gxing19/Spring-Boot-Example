package com.springboot.aop.common.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Aspect
//@Component
public class RequestHandleTimeAspect {
    private static final Logger logger = LogManager.getLogger(RequestHandleTimeAspect.class);

    @Autowired
    private HttpServletRequest request;

    @Pointcut("execution(* com.springboot.aop.controller.*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //开始时间
        long startTime = System.currentTimeMillis();

        //执行代理目标方法
        Object obj = joinPoint.proceed();
        Thread.sleep(200);

        //结束时间
        long endTime = System.currentTimeMillis();

        //处理时间
        long costTime = endTime - startTime;

        Object target = joinPoint.getTarget();
        //代理目标对象类名
        String className = target.getClass().getSimpleName();
        //代理目标对象全限定类名
        String fullClassName = target.getClass().getName();
        //切点的方法名
        String methodName = joinPoint.getSignature().getName();
        //请求URI
        String requestURI = request.getRequestURI();

        //获取HttpServletResponse来自定义响应数据
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        if (costTime > 180) {
            logger.info("【WARN】URI:{}, API:{}, costTime:{} millis", requestURI, className + "." + methodName, costTime);
        } else {
            logger.info("【NORMAL】处理方法:{}, 处理请求耗时:{}", fullClassName + "." + methodName, (endTime - startTime));
        }
        return obj;
    }
}