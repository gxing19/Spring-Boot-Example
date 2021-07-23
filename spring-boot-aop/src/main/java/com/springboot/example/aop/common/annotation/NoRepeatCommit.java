package com.springboot.example.aop.common.annotation;

import java.lang.annotation.*;

/**
 * 注解支持作用在类上,则所有方法都需要做防重复提交
 * 类和方法上都存在,则方法优化级更高
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface NoRepeatCommit {
}
