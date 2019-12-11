package com.springboot.datasource.common.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置类
 */
@Configuration
@MapperScan(basePackages = "com.springboot.datasource.mapper")
public class DataSourceConfig {

    @Bean(name = "dataSourceMaster")
    @ConfigurationProperties("spring.datasource.master")
    public DataSource dataSourceMaster() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dataSourceSlave")
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource dataSourceSlave() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("dataSourceMaster") DataSource dataSourceMaster,
                                        @Qualifier("dataSourceSlave") DataSource dataSourceSlave) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(dataSourceMaster);
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("master", dataSourceMaster);
        targetDataSources.put("slave", dataSourceSlave);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }
}
