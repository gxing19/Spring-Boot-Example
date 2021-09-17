package com.springboot.datasource.common.datasource.annotation;

import com.springboot.datasource.common.datasource.DataSourceEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据源选择注解
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSourceSelector {
    DataSourceEnum name();
}
