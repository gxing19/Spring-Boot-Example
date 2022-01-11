package com.gxitsky.common.component;

import com.gxitsky.common.annotation.NoRepeatCommit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Order(Integer.MAX_VALUE - 800)
public class NoRepeatInterceptor implements HandlerInterceptor {
    private static final Logger logger = LogManager.getLogger(NoRepeatInterceptor.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 通过判断 Session 中的 Token 来处理重复提交
     * 适用于单体应用,或使用了共享 Session 方案的集群系统
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("进入防止重复提交拦截器.............");

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?> beanType = handlerMethod.getBeanType();
        //注解支持作用在类上,则所有方法都需要做防重复提交
        NoRepeatCommit classNoRepeat = beanType.getAnnotation(NoRepeatCommit.class);
        NoRepeatCommit MethodNoRepeat = handlerMethod.getMethodAnnotation(NoRepeatCommit.class);
        //方法注解优先
        NoRepeatCommit noRepeatCommit = (null != MethodNoRepeat ? MethodNoRepeat : classNoRepeat);
        if (null != noRepeatCommit) {
            //如果有防重复提交的注解,则进入业务处理
            HttpSession session = request.getSession();
            String sessionNoRepeatToken = (String) session.getAttribute("NO-REPEAT");
            String noRepeatParam = request.getParameter("NO-REPEAT");
            if (null != sessionNoRepeatToken && sessionNoRepeatToken.equals(noRepeatParam)) {
                session.removeAttribute("NO-REPEAT");
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
