package com.springboot.aop.common.aop;

import com.springboot.aop.common.bean.ResultHelper;
import com.springboot.aop.common.configuration.RedisLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.UUID;

@Aspect
@Component
public class NoRepeatCommitAspect {
    private static final Logger logger = LogManager.getLogger(NoRepeatCommitAspect.class);

    @Autowired
    private RedisLock redisLock;

    //    @Pointcut("@annotation(com.springboot.demo.common.annotation.NoRepeatCommit)")
    @Pointcut("@annotation(com.springboot.aop.common.annotation.NoRepeatCommit)")
    public void pointcut() {
    }

    ;

    @Around("pointcut()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("进入防止重复提交切面..........");
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

            // 此处可以用token或者sessionId
            String token = request.getHeader("Authorization");
            String uri = request.getRequestURI();
            String key = DigestUtils.md5DigestAsHex((token + uri).getBytes(Charset.defaultCharset()));
            String sessionId = request.getRequestedSessionId();
            String clientId = UUID.randomUUID().toString().replace("-", "");

            boolean isSuccess = redisLock.tryLock(key, clientId, 10L);
            logger.info("RedisLock Key:{}, Value:{}", key, clientId);
            if (isSuccess) {
                logger.info("RedisLock tryLock Success");
                Object result = joinPoint.proceed();
                redisLock.releaseLock(key, clientId);
                return result;
            } else {
                //获取锁失败,认为是重复提交的请求
                logger.info("RedisLock tryLock Fail");
                return ResultHelper.repeatCommit();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return ResultHelper.sysError();
    }


}
