package com.gxitsky.common.aop;

import com.gxitsky.common.annotation.RequestLimit;
import com.gxitsky.common.utils.IPUtil;
import com.gxitsky.entity.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.SourceLocation;
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

//@Aspect
//@Component
public class RequestLimitAspectA{

    private static final Logger logger = LogManager.getLogger(RequestLimitAspectA.class);

    HashMap<String, Integer> map = new HashMap<>();

    /*@Autowired
    private RedisTemplate<String, Object> redisTemplate;*/

    /**
     * 指定注解
     * @param requestLimit
     */
    @Pointcut("@annotation(requestLimit)")
    public void pointcut(RequestLimit requestLimit) {
    }

    //ProceedingJoinPoint is only supported for around advice
//    @Pointcut("within(org.springframework.web.bind.annotation.RestController) && @annotation(requestLimit)")
//    @Pointcut("within(org.springframework.web.bind.annotation.RestController) && @annotation(requestLimit)")
    @Before("pointcut(requestLimit)")
    public void before(JoinPoint joinPoint, RequestLimit requestLimit) throws IOException {

        //获取目标方法的参数信息
        Object[] args = joinPoint.getArgs();
        //AOP代理类的信息RequestLimitAspect
        Object aThis = joinPoint.getThis();
        //代理的目标对象
        Object target = joinPoint.getTarget();
        String kind = joinPoint.getKind();
        //用的最多,通知的签名(代理的目标方法签名)
        Signature signature = joinPoint.getSignature();
        //代理的是哪一个方法
        String method = signature.getName();
        //AOP代理类的类(class)信息
        Class declaringType = signature.getDeclaringType();
        //AOP代理类的名字
        String declaringTypeName = signature.getDeclaringTypeName();
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //获取 sessionId
        String sessionId = requestAttributes.getSessionId();
        //获取参数 0-Request,1-Session
        String[] attributeNames = requestAttributes.getAttributeNames(0);
        Object sessionMutex = requestAttributes.getSessionMutex();
        //如果要获取Session信息的话，可以这样写：
        HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request1 = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        HttpServletRequest request2 = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //执行切点位置
        SourceLocation sourceLocation = joinPoint.getSourceLocation();

        int maxCount = requestLimit.count();
        int second = requestLimit.second();
        Account account = null;
        HttpSession session1 = request1.getSession();
        String ip = IPUtil.getIpFromRequest(request1);
        String key = session.getId() + ":" + request1.getRequestURI() + ":" + ip;
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
