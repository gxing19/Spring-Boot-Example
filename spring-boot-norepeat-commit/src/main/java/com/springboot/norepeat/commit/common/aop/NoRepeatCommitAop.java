package com.springboot.norepeat.commit.common.aop;

import com.springboot.norepeat.commit.common.annotation.NoRepeatCommit;
import com.springboot.norepeat.commit.common.constant.ConstantParm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class NoRepeatCommitAop {
    private static final Logger logger = LogManager.getLogger(NoRepeatCommitAop.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Pointcut("execution(* com.springboot.norepeat.commit.controller..*(..)) && @annotation(noRepeatCommit)")
    public void pointcut(NoRepeatCommit noRepeatCommit) {
    }

    @Before("pointcut(noRepeatCommit)")
    public void before(JoinPoint joinPoint, NoRepeatCommit noRepeatCommit) throws IOException {

        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //获取HttpServletRequest
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        //获取HttpServletResponse
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();

        String noRepeatToken = request.getHeader(ConstantParm.TOKEN_PRE);
        if (null != noRepeatToken) {
            String key = ConstantParm.TOKEN_PRE + ":" + noRepeatToken;

            //使用 Redis Lua 脚本实现判断和删除属于同一个原子操作
            long result = redisToken(key);
            if(result != 1L){
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                ServletOutputStream output = response.getOutputStream();
                output.write("{\"msg\":\"请不要重复提交\"}".getBytes("UTF-8"));
                output.flush();
                output.close();
            }

            //使用同步锁防止并发,判断和删除同时只能有一个线程操作
            /*synchronized (this) {
                boolean exist = redisTemplate.hasKey(key);
                if (exist) {
                    redisTemplate.delete(key);
                } else {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=UTF-8");
                    ServletOutputStream output = response.getOutputStream();
                    output.write("{\"msg\":\"请不要重复提交\"}".getBytes("UTF-8"));
                    output.flush();
                    output.close();
                }
            }*/

            //不使用同步锁,存在并发问题,多线程可能都判断得到 key 存在
            /*boolean exist = redisTemplate.hasKey(key);
            if (exist) {
                redisTemplate.delete(key);
            } else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                ServletOutputStream output = response.getOutputStream();
                output.write("{\"msg\":\"请不要重复提交\"}".getBytes("UTF-8"));
                output.flush();
                output.close();
            }*/

        } else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            ServletOutputStream output = response.getOutputStream();
            output.write("{\"msg\":\"请重新初始化表单\"}".getBytes("UTF-8"));
            output.flush();
            output.close();
        }
    }

    private long redisToken(String key) {
        String luaScript = "if (redis.call('get', KEYS[1]) == ARGV[1]) then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript, Long.class);
        List<String> keyList = new ArrayList<>();
        keyList.add(key);
        long result = redisTemplate.execute(redisScript, keyList, "");
        return result;
    }
}
