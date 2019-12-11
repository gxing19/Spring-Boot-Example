package com.springboot.datasource.common.datasource;

/**
 * 存放线程数据源
 */
public class DataSourceHolder {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setDataSource(String key){
        threadLocal.set(key);
    }

    public static String getDataSource() {
        return threadLocal.get();
    }

    public static void cleanDataSource(){
        threadLocal.remove();
    }
}
