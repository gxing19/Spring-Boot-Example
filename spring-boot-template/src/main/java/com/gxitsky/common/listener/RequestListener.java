package com.gxitsky.common.listener;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @name: RequestListener
 * @desc: 给请求增加全局唯一ID给日志输出用
 **/
@Component
@WebListener
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        //给请求增加全局唯一ID给日志输出用
        long logId = System.currentTimeMillis();
        ThreadContext.put("LogId", String.valueOf(logId));
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }
}