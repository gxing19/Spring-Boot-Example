package com.springboot.example.aop.common.component;

import com.alibaba.fastjson.JSON;
import com.springboot.example.aop.entity.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Order(Integer.MAX_VALUE - 1000)
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LogManager.getLogger(LoginInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("进入登录验证拦截器.............");
        HttpSession session = request.getSession();
        Account authUser = (Account) session.getAttribute("auth_user");
        if (!ObjectUtils.isEmpty(authUser)) {
            logger.info("用户已登录:{}", JSON.toJSONString(authUser));
            return true;
        } else {
            logger.info("用户未登录.................");
            ServletOutputStream output = response.getOutputStream();
            output.write("{\"msg\":\"请先登录\"}".getBytes("UTF-8"));
            output.flush();
            output.close();
            return false;
        }

    }
}
