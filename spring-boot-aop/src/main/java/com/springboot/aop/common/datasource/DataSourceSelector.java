package com.springboot.aop.common.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceSelector {

    @Autowired
    private DataSource dataSourceMaster;
    @Autowired
    private DataSource dataSourceSlave;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(dataSourceMaster);

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("master", dataSourceMaster);
        dataSourceMap.put("slave", dataSourceSlave);
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }

}
