package com.gxitsky.common.datasource.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

@Order(Integer.MAX_VALUE - 1000)//此是重点,Order值必须大于动态数据源切换AOP的Order值
@Aspect
@Component
@Scope("prototype")//线程安全,每次调用都是一个新的实例
public class TransactionalAop1 {

    private TransactionStatus transactionStatus;

    @Autowired
    private TransactionUtils transactionUtils;

    /**
     * 切点 Service 层
     */
    @Pointcut("execution(* com.springboot.datasource.service..*(..))")
    public void servicePointcut() {
    }

    /**
     * 切点 Transactional 注解
     */
    @Pointcut("@annotation(transactional)")
    public void txAnnotationPointcut(Transactional transactional) {
    }

    /**
     * 开启事务
     * @param joinPoint
     * @param transactional
     * @throws Throwable
     */
    @Before(value = "servicePointcut() && txAnnotationPointcut(transactional)", argNames = "joinPoint,transactional")
    public void before(JoinPoint joinPoint, Transactional transactional) throws Throwable {
        // 1 找到对应的方法，通过方法名和参数类型 因为会有重载
        this.transactionStatus = transactionUtils.begin(transactional);
    }

    /**
     * 正常返回,提交事务
     * @param transactional
     */
    @AfterReturning(value = "servicePointcut() && txAnnotationPointcut(transactional)", argNames = "transactional")
    public void afterReturning(Transactional transactional) {
        transactionUtils.commit(transactionStatus);
    }

    /**
     * 异常通知,回滚事务
     * @param transactional
     */
    @AfterThrowing(value = "servicePointcut() && txAnnotationPointcut(transactional)", argNames = "transactional")
    public void afterThrowing(Transactional transactional) {
        transactionUtils.rollback(transactionStatus);
    }

}
