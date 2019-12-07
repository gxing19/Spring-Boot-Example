package com.springboot.aop.common.datasource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataSourceContextHolder {
    private static final Logger logger = LogManager.getLogger(DataSourceContextHolder.class);

    public static final String DEFAULT_DS = "master";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSource(String dsName) {
        logger.info("切换到 {} 数据源", dsName);
        contextHolder.set(dsName);
    }

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void cleanDataSource() {
        contextHolder.remove();
    }
}
