package com.springboot.norepeat.commit.common.component;

import com.springboot.norepeat.commit.common.annotation.NoRepeatCommit;
import com.springboot.norepeat.commit.common.constant.ConstantParm;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoRepeatInterceptor implements HandlerInterceptor {
    private static final Logger logger = LogManager.getLogger(NoRepeatInterceptor.class);

    private RedisTemplate<String, Object> redisTemplate;

    public NoRepeatInterceptor(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("进入防止重复提交拦截器.............");

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?> beanType = handlerMethod.getBeanType();
        //注解支持作用在类上,则所有方法都需要做防重复提交
        NoRepeatCommit classNoRepeat = beanType.getAnnotation(NoRepeatCommit.class);
        NoRepeatCommit MethodNoRepeat = handlerMethod.getMethodAnnotation(NoRepeatCommit.class);
        //方法注解优先
        NoRepeatCommit noRepeatCommit = (null != MethodNoRepeat ? MethodNoRepeat : classNoRepeat);
        if (null != noRepeatCommit) {
            /*Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(ConstantParm.TOKEN_PRE)) {
                    String noRepeatToken = cookie.getValue();
                }
            }*/
            String noRepeatToken = request.getHeader(ConstantParm.TOKEN_PRE);
            String key = ConstantParm.TOKEN_PRE + ":" + noRepeatToken;
            //如果有防重复提交的注解,则进入业务处理
            if (null != noRepeatToken) {
                synchronized (this) {
                    boolean exist = redisTemplate.hasKey(key).booleanValue();
                    if (exist) {
                        redisTemplate.delete(key);
                    }
                }
                return true;
            } else {
                //如果防重复提交 Token 不存在,或不相等,则认为已处理并拒绝提交
                ServletOutputStream output = response.getOutputStream();
                output.write("{\"msg\":\"请不要重复提交\"}".getBytes("UTF-8"));
                output.flush();
                output.close();
                return false;
            }
        }

        return true;
    }
}
