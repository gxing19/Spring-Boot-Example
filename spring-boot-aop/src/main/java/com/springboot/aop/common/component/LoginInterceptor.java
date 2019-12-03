package com.springboot.aop.common.component;

import com.alibaba.fastjson.JSON;
import com.springboot.aop.entity.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Order(Integer.MAX_VALUE - 500)
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LogManager.getLogger(LoginInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("登录验证拦截器.............");

        HttpSession session = request.getSession();
        Account authUser = (Account) session.getAttribute("auth_user");
        if (ObjectUtils.isEmpty(authUser)) {
            logger.info("用户未登录.................");
            return false;
        }
        logger.info("用户已登录:{}", JSON.toJSONString(authUser));
        return true;
    }
}
