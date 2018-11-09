package com.springboot.template.common.interceptor;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @name: LogIdInterceptor
 * @desc: TODO
 * @author: gxing
 * @date: 2018-11-09 17:45
 **/
@Component
public class LogIdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        long logId = System.currentTimeMillis();
        ThreadContext.put("LogId",String.valueOf(logId));
        return true;
    }
}