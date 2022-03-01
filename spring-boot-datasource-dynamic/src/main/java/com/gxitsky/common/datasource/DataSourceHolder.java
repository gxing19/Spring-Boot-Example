package com.gxitsky.common.datasource;

/**
 * 存放线程数据源
 */
public class DataSourceHolder {

    private static final ThreadLocal<DataSourceEnum> threadLocal = new ThreadLocal<>();

    public static void setDataSource(DataSourceEnum key){
        threadLocal.set(key);
    }

    public static DataSourceEnum getDataSource() {
        return threadLocal.get();
    }

    public static void cleanDataSource(){
        threadLocal.remove();
    }
}
