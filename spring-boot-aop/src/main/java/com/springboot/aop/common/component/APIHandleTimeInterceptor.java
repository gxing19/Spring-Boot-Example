package com.springboot.aop.common.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.annotation.Order;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.mvc.condition.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Order(Integer.MAX_VALUE - 900)
public class APIHandleTimeInterceptor implements HandlerInterceptor, ApplicationContextAware  {
    private static final Logger logger = LogManager.getLogger(APIHandleTimeInterceptor.class);

    private NamedThreadLocal<Long> threadLocal = new NamedThreadLocal("StopWatch_StartTime");

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("API 处理请求拦截器.............");

        this.webApplicationContext = (WebApplicationContext) request.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        Set<String> uri = new HashSet<>();
        RequestMappingHandlerMapping mapping = webApplicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
        for (RequestMappingInfo requestMappingInfo : handlerMethods.keySet()) {
            String name = requestMappingInfo.getName();
            ConsumesRequestCondition consumesCondition = requestMappingInfo.getConsumesCondition();
            RequestCondition<?> customCondition = requestMappingInfo.getCustomCondition();
            HeadersRequestCondition headersCondition = requestMappingInfo.getHeadersCondition();
            RequestMappingInfo matchingCondition = requestMappingInfo.getMatchingCondition(request);
            ParamsRequestCondition paramsCondition = requestMappingInfo.getParamsCondition();
            PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
            Set<String> patterns = patternsCondition.getPatterns();
            uri.addAll(patterns);
            ProducesRequestCondition producesCondition = requestMappingInfo.getProducesCondition();
            System.out.println(requestMappingInfo);
        }


        logger.info("请求进入API处理时长拦截器.................");
        //1.开始时间
        long startTime = System.currentTimeMillis();
        //2.绑定变量(只有当前请求线程可见)
        threadLocal.set(startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        //3.结束时间(请求处理完成)
        long endTime = System.currentTimeMillis();
        //4.取出开始时间
        long startTime = threadLocal.get();
        //5.计算耗时
        long consumeTime = endTime - startTime;
        //此处认为处理时间超过500毫秒的请求为慢请求
        //此处可以做一些复杂的告警通知,如与 IM即时通信应用、邮件系统、短信系统对接
        if (consumeTime > 500) {
//            logger.info(String.format("【WARN】%s consume %d millis", request.getRequestURI(), consumeTime));
            logger.info("【WARN】API:{}, consumeTime:{} millis", request.getRequestURI(), consumeTime);
        } else {
//            logger.info(String.format("【NORMAL】%s consume %d millis", request.getRequestURI(), consumeTime));
            logger.info("【NORMAL】API:{}, consumeTime:{} millis", request.getRequestURI(), consumeTime);
        }
        logger.info("请示处理完成从API处理时长拦截器结束.................");

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
    }

}
