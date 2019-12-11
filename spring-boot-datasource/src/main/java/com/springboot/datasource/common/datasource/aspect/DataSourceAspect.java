package com.springboot.datasource.common.datasource.aspect;

import com.springboot.datasource.common.datasource.DataSourceConstant;
import com.springboot.datasource.common.datasource.DataSourceHolder;
import com.springboot.datasource.common.datasource.annotation.DataSourceSelector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 数据源动态切换 AOP
 */
@Aspect
@Component
public class DataSourceAspect {
    private static final Logger logger = LogManager.getLogger(DataSourceAspect.class);

    @Pointcut("execution(* com.springboot.datasource.mapper..*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void switchDataSource(JoinPoint joinPoint) {
        DataSourceHolder.cleanDataSource();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        DataSourceSelector dsSelector = methodSignature.getMethod().getAnnotation(DataSourceSelector.class);
        //如果有注解,则使用注解指定的数据源;否则,根据方法名的前缀业判断读/写操作
        if (null != dsSelector) {
            String name = dsSelector.name();
            DataSourceHolder.setDataSource(name);
            logger.info("Method Name:{}, DataSource:{}", methodName, name);
        } else if (methodName.startsWith("get") || methodName.startsWith("query") || methodName.startsWith("find")
                || methodName.startsWith("select") || methodName.startsWith("list")) {
            DataSourceHolder.setDataSource(DataSourceConstant.SLAVE);
            logger.info("Method Name:{}, DataSource:{}", methodName, DataSourceConstant.SLAVE);
        } else {
            DataSourceHolder.setDataSource(DataSourceConstant.MASTER);
            logger.info("Method Name:{}, Datasource:{}", methodName, DataSourceConstant.SLAVE);
        }

    }
}
