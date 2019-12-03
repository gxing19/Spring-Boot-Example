package com.springboot.repeatcommit.common.config;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.UUID;

@Aspect
@Component
@EnableAspectJAutoProxy(exposeProxy = true)
public class AvoidRepeatableCommitAspect {

    @Autowired
    HttpServletRequest request; //这里可以获取到request

    /**
     * @param point
     */
    @Around("@annotation(com.springboot.repeatcommit.common.config.AvoidRepeatableCommit)")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        String ip = IPUtil.getIPAddress(request);
        //获取注解
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //目标类、方法
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String ipKey = String.format("%s#%s", className, methodName);
        int hashCode = Math.abs(ipKey.hashCode());
        String key = String.format("%s_%d", ip, hashCode);
//        log.info("ipKey={},hashCode={},key={}",ipKey,hashCode,key);
        AvoidRepeatableCommit avoidRepeatableCommit = method.getAnnotation(AvoidRepeatableCommit.class);
        long timeout = avoidRepeatableCommit.timeout();
        if (timeout < 0L) {
            timeout = 5L;
        }
        //用多参数set方法保证对redis操作原子性
        boolean result = RedisUtil.setnxAndExpire(key, UUID.randomUUID().toString(), timeout * 1000);
        if (!result) {
            HashMap<String,Object> resultMap = new HashMap<>(5);
            resultMap.put("errCode", 10001);
            resultMap.put("errMsg", "请勿重复提交");
            return JSON.toJSONString(resultMap);
        }
        //执行方法
        Object object = point.proceed();
        return object;
    }

}
