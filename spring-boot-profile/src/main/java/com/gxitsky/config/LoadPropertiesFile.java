package com.gxitsky.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 加载指定位置配置文件
 */
@Configuration
@PropertySource(value = {"classpath:mysql.properties"})
public class LoadPropertiesFile {

}
