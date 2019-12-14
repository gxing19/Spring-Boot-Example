package com.springboot.datasource.common.datasource.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import java.lang.reflect.Method;

//@Order(Integer.MAX_VALUE - 1000)
//@Aspect
//@Component
//@Scope("prototype")
public class TransactionalAop2 {
    private static final Logger logger = LogManager.getLogger(TransactionalAop2.class);

    private TransactionStatus transactionStatus;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Pointcut("execution(* com.springboot.datasource.service..*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public void before(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?> clazz = joinPoint.getTarget().getClass();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String methodName = methodSignature.getName();
        Class[] parameterTypes = methodSignature.getParameterTypes();
        Transactional transactional = method.getDeclaredAnnotation(Transactional.class);
        if (null != transactional) {
            this.begin(transactional);
            joinPoint.proceed();
            if (transactionStatus != null) {
                logger.info("Commit Transactional");
                transactionManager.commit(transactionStatus);
            }
        }

    }

    // 异常通知
    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        logger.info("Throw Exception, Transactional Rollback");
        if (transactionStatus != null) {
            //事务回滚
            transactionManager.rollback(transactionStatus);
        }
    }

    // 开启事务
    public void begin(Transactional transactional) {
        DefaultTransactionAttribute txAttribute = new DefaultTransactionAttribute();
        txAttribute.setQualifier(transactional.value());
        txAttribute.setIsolationLevel(transactional.isolation().value());
//        txAttribute.setIsolationLevelName(transactional.isolation().name());
        txAttribute.setPropagationBehavior(transactional.propagation().value());
//        txAttribute.setPropagationBehaviorName(transactional.propagation().name());
        txAttribute.setTimeout(transactional.timeout());
        txAttribute.setReadOnly(transactional.readOnly());
        this.transactionStatus = transactionManager.getTransaction(new DefaultTransactionAttribute());
    }
}
