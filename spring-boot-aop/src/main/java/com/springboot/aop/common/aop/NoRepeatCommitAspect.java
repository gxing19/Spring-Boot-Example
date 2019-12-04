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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Aspect
@Component
public class NoRepeatCommitAspect {
    private static final Logger logger = LogManager.getLogger(NoRepeatCommitAspect.class);

    @Autowired
    private RedisLock redisLock;

    @Pointcut("@annotation(com.springboot.aop.common.annotation.NoRepeatCommit)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("进入防止重复提交切面..........");

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpSession session = request.getSession();
        String sessionNoRepeatToken = (String) session.getAttribute("NO-REPEAT");
        String requestNoRepeatToken = request.getParameter("NO-REPEAT");

        if (null != sessionNoRepeatToken && sessionNoRepeatToken.equals(requestNoRepeatToken)) {
            Object result = joinPoint.proceed();
            session.removeAttribute("NO-REPEAT");
            return result;
        } else {
            //如果防重复提交 Token 不存在,或不相等,则认为已处理并拒绝提交
//            ServletOutputStream output = response.getOutputStream();
//            output.write("{\"msg\":\"请不要重复提交\"}".getBytes("UTF-8"));
//            output.flush();
//            output.close();
            return ResultHelper.repeatCommit();
        }
    }
}
