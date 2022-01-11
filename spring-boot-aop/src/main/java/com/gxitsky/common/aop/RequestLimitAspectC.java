package com.gxitsky.common.aop;

import com.gxitsky.common.utils.IPUtil;
import com.gxitsky.entity.Account;
import com.gxitsky.common.annotation.RequestLimit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.HashMap;

//@Aspect
//@Component
public class RequestLimitAspectC {

    private static final Logger logger = LogManager.getLogger(RequestLimitAspectC.class);

    HashMap<String, Integer> map = new HashMap<>();

    /*@Autowired
    private RedisTemplate<String, Object> redisTemplate;*/

//    @Pointcut("execution(* com.springboot.aop.controller.*.*(..))")
//    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(requestLimit)")
    @Pointcut("within(@com.gxitsky.common.annotation.RequestLimit *)")
    public void pointcut() {
    }

    //ProceedingJoinPoint is only supported for around advice
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) throws IOException, NoSuchMethodException {

        //代理的目标对象
        Object target = joinPoint.getTarget();
        //通知的签名(代理的目标方法签名)
        Signature signature = joinPoint.getSignature();

        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //获取HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //获取HttpServletResponse
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
        //获取Session Id
        String sessionId = requestAttributes.getSessionId();
        //获取IP
        String ip = IPUtil.getIpFromRequest(request);

        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        RequestLimit methodRequestLimit = method.getAnnotation(RequestLimit.class);
        RequestLimit classRequestLimit = target.getClass().getAnnotation(RequestLimit.class);
        RequestLimit requestLimit = (null != methodRequestLimit ? methodRequestLimit : classRequestLimit);

        int maxCount = requestLimit.count();
        int second = requestLimit.second();

        String key =sessionId + ":" + request.getRequestURI() + ":" + ip;
        if (requestLimit.needLogin()) {
            Account account = (Account) request.getSession().getAttribute("auth_user");
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
//            redisTemplate.opsForValue().setIfAbsent(key, 1, second, TimeUnit.SECONDS);
        } else if (count < maxCount) {
            //加 1
            map.put(key, map.get(key) + 1);
//            redisTemplate.opsForValue().set(key, count + 1);
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
