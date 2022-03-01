package com.gxitsky.common.datasource.aspect;

import com.gxitsky.common.datasource.DataSourceEnum;
import com.gxitsky.common.datasource.DataSourceHolder;
import com.gxitsky.common.datasource.annotation.DataSourceSelector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * 数据源动态切换 AOP
 */
@Order(Integer.MAX_VALUE - 2000)
@Aspect
@Component
public class DataSourceAspect {
    private static final Logger logger = LogManager.getLogger(DataSourceAspect.class);

    @Pointcut("execution(* com.springboot.datasource.service..*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object switchDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String methodName = method.getName();

        DataSourceSelector dsSelector = method.getAnnotation(DataSourceSelector.class);
        Transactional transactional = method.getAnnotation(Transactional.class);

        if (null != dsSelector) {
            //显式指定数据源优先
            DataSourceEnum dataSourceKey = dsSelector.name();
            DataSourceHolder.setDataSource(dataSourceKey);
            logger.info("DataSourceSelector name:{}, DataSource:{}", dsSelector.name(), dataSourceKey);
        } else if (null != transactional && transactional.readOnly()) {
            //读事务路由到从库
            DataSourceHolder.setDataSource(DataSourceEnum.SLAVE);
            logger.info("Transactional readOnly:{}, DataSource:{}", transactional.readOnly(), DataSourceEnum.SLAVE);
        } else if (methodName.startsWith("get") || methodName.startsWith("query") || methodName.startsWith("find")
                || methodName.startsWith("select") || methodName.startsWith("list")) {
            //根据方法前缀判断路由到从库
            DataSourceHolder.setDataSource(DataSourceEnum.SLAVE);
            logger.info("Method Name:{}, DataSource:{}", methodName, DataSourceEnum.SLAVE);
        } else {
            //其它到主库
            DataSourceHolder.setDataSource(DataSourceEnum.MASTER);
            logger.info("Method Name:{}, Datasource:{}", methodName, DataSourceEnum.MASTER);
        }

        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            //这里必须抛出异常才会触发事务回滚
            throw throwable;
        } finally {
            DataSourceHolder.cleanDataSource();
        }
    }
}
