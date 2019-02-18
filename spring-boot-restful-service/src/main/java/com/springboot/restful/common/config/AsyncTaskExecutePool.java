package com.springboot.restful.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @desc: 自定义异步线程池
 **/
@Configuration
public class AsyncTaskExecutePool implements AsyncConfigurer {
    private Logger logger = LoggerFactory.getLogger(AsyncTaskExecutePool.class);

    @Override
    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        /*核收线程数*/
        executor.setCorePoolSize(5);
        /*最大线程数*/
        executor.setMaxPoolSize(50);
        /*线程被关闭前的空闲时间*/
        executor.setKeepAliveSeconds(60);
        /*队列长度*/
        executor.setQueueCapacity(10000);
        /*设置线程名前缀*/
        executor.setThreadNamePrefix("....");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler(){
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable ex, Method method, Object... params) {
                logger.error(ex.getMessage() + "--------" + ex);
                logger.error("exception method:" + method.getName());
            }
        };

    }
}
