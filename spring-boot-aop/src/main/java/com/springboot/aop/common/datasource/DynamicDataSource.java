package com.springboot.aop.common.datasource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger logger = LogManager.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("选择使用 {} 数据源", DataSourceContextHolder.getDataSource());
        return DataSourceContextHolder.getDataSource();
    }

}
