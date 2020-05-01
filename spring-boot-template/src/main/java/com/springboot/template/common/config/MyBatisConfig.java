package com.springboot.template.common.config;

import com.springboot.template.mapper.base.BaseMapper;
import org.springframework.context.annotation.Configuration;


/**
 * @name: MyBatisConfig
 * @desc: mybatis配置类, 扫描mapper包, 自定义继承mapper<T>接口的基础接口不能被扫描
 * @author: gxing
 * @date: 2018-09-25 15:23
 **/
@tk.mybatis.spring.annotation.MapperScan(
        basePackages = "com.springboot.template.mapper", markerInterface = BaseMapper.class)
@Configuration
public class MyBatisConfig {
}
