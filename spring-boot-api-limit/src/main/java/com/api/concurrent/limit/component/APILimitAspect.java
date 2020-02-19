package com.api.concurrent.limit.component;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.LoadingCache;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.MappingMatch;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Aspect
@Component
public class APILimitAspect {
    private static final Logger logger = LogManager.getLogger(APILimitAspect.class);

    /*@Autowired
    private HttpServletRequest request;
    @Autowired
    LoadingCache<String, AtomicLong> loadingCache;

    @Pointcut("execution(* com.api.concurrent.limit.Controller.*.*(..)) && @annotation(apiLimit)")
    public void pointcut(ApiLimit apiLimit) {
    }

    @Before("pointcut(apiLimit)")
    public void before(JoinPoint joinPoint, ApiLimit apiLimit) throws ExecutionException, IOException {
        //限流次数
        long times = apiLimit.times();
        long currentSeconds = System.currentTimeMillis() / 1000;

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();

        if (loadingCache.get(String.valueOf(currentSeconds)).incrementAndGet() > times) {
            String msg = "xxxxxxx限流了........." + "Second:" + currentSeconds + ", " + loadingCache.get(String.valueOf(currentSeconds));
            System.out.println(msg);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            ServletOutputStream output = response.getOutputStream();
            output.write(msg.getBytes("UTF-8"));
            output.flush();
            output.close();
        }
    }*/


    /**
     *
     */
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Pointcut("execution(* com.api.concurrent.limit.Controller.*.*(..)) && @annotation(redisLuaLimit)")
    public void pointcut(RedisLuaLimit redisLuaLimit) {
    }

    @Before("pointcut(redisLuaLimit)")
    public void before(JoinPoint joinPoint, RedisLuaLimit redisLuaLimit) throws IOException {
        //限流次数
        long maxLimit = redisLuaLimit.limit();
        long second = System.currentTimeMillis() / (1000);

        String requestURI = request.getRequestURI();
        String key = requestURI + ":" + String.valueOf(second);
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();

//        ResourceLoader resourceLoader = new DefaultResourceLoader();
//        Resource resource = resourceLoader.getResource("classpath:limit.lua");
        ClassPathResource resource = new ClassPathResource("limit.lua");
        String luaScript = Files.asCharSource(resource.getFile(), Charset.defaultCharset()).read();

        RedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript, Long.class);
        List<String> keyList = new ArrayList<>();
        keyList.add(String.valueOf(key));
        //注意,传入 lua 脚本的所有参数都被认为是 String 类型, lua 脚本需要使有 tonumber() 转换
        Long result = redisTemplate.execute(redisScript, keyList, maxLimit);

        if (null != result && result == 0L) {
            String msg = "xxxxxxx限流了........." + "Second:" + key;
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            ServletOutputStream output = response.getOutputStream();
            output.write(msg.getBytes("UTF-8"));
            output.flush();
            output.close();
        }
    }

}