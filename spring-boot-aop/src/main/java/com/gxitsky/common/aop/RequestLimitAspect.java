package com.gxitsky.common.aop;

import com.gxitsky.common.utils.IPUtil;
import com.gxitsky.entity.Account;
import com.gxitsky.common.annotation.RequestLimit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

//@Aspect
//@Component
public class RequestLimitAspect {

    private static final Logger logger = LogManager.getLogger(RequestLimitAspect.class);

    HashMap<String, Integer> map = new HashMap<>();

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 切点传入注解参数使用这种方式
     * @param requestLimit
     */
//    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(requestLimit)")
    @Pointcut("@annotation(requestLimit)")
    public void pointcut(RequestLimit requestLimit) {
    }

    //ProceedingJoinPoint is only supported for around advice
    @Before("pointcut(requestLimit)")
    public void around(JoinPoint joinPoint, RequestLimit requestLimit) throws IOException {

        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //获取HttpServletRequest
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        //获取HttpServletResponse
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();

        int maxCount = requestLimit.count();
        int second = requestLimit.second();

        Account account = null;
        String ip = IPUtil.getIpFromRequest(request);
        HttpSession session = request.getSession();
        String key = session.getId() + ":" + request.getRequestURI() + ":" + ip;
        if (requestLimit.needLogin()) {
            account = (Account) session.getAttribute("auth_user");
            if (null == account) {
                throw new RuntimeException("访问必须先登录");
            }
            key = key + ":" + account.getId().toString();
        }
        key = "RequestLimit:" + DigestUtils.md5DigestAsHex(key.getBytes(Charset.defaultCharset()));
        Integer count = map.get(key);
//        Integer count = (Integer) redisTemplate.opsForValue().get(key);
        if (null == count) {
            //首次请求
            map.put(key, 1);
            redisTemplate.opsForValue().setIfAbsent(key, 1, second, TimeUnit.SECONDS);
        } else if (count < maxCount) {
            //加 1
            map.put(key, map.get(key) + 1);
            redisTemplate.opsForValue().set(key, count + 1);
        } else {
            logger.info("接口请求次数超限制:{}", key);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            ServletOutputStream output = response.getOutputStream();
            output.write("{\"msg\":\"请求次数超出限制\"}".getBytes("UTF-8"));
            output.flush();
            output.close();
        }
    }
}
