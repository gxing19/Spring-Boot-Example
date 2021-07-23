package com.springboot.example.aop.common.component;

import com.springboot.example.aop.common.annotation.RequestLimit;
import com.springboot.example.aop.common.utils.IPUtil;
import com.springboot.example.aop.entity.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.util.DigestUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.HashMap;

@Order(Integer.MAX_VALUE - 100)
public class RequestLimitInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LogManager.getLogger(RequestLimitInterceptor.class);

    private HashMap<String, Integer> map = new HashMap<>();

    /*@Autowired
    private RedisTemplate<String, Object> redisTemplate;*/

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("请求限制拦截器.............");

        //判断是否属于方法的请求
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            //获取方法中的注解,判断是否有注解
            RequestLimit methodReqLimit = handlerMethod.getMethodAnnotation(RequestLimit.class);
            RequestLimit classReqLimit = method.getDeclaringClass().getAnnotation(RequestLimit.class);
            //方法注解优先(粒度更小)
            RequestLimit requestLimit = (methodReqLimit != null ? methodReqLimit : classReqLimit);
            if (null == requestLimit) {
                //未限制
                return true;
            }

            int second = requestLimit.second();
            int maxCount = requestLimit.count();
            boolean needLogin = requestLimit.needLogin();
            String key = request.getRequestURI();
            //如果需要登录(如果有前置登录拦截器,这里可以省略)
            if (needLogin) {
                HttpSession session = request.getSession();
                Account account = (Account) session.getAttribute("auth_user");
                if (null == account) {
                    //未登录
                    return false;
                } else {
                    key = key + ":" + account.getId();
                }
            }
            //获取IP(或使用 SessionId)
            key = key + ":" + IPUtil.getIpFromRequest(request);
            key = DigestUtils.md5DigestAsHex(key.getBytes(Charset.defaultCharset()));
            Integer count = map.get(key);
//            Integer count = (Integer) redisTemplate.opsForValue().get(key);
            if (null == count) {
                //表示第一次访问
                map.put(key, 1);
//                redisTemplate.opsForValue().setIfAbsent(key, count, second, TimeUnit.SECONDS);
            } else if (count < maxCount) {
                //加1
                map.put(key, map.get(key) + 1);
//                redisTemplate.opsForValue().increment(key);
            } else {
                logger.info("接口请求次数超限制:{}", key);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                ServletOutputStream output = response.getOutputStream();
                output.write("{\"msg\":\"请求次数超出限制\"}".getBytes("UTF-8"));
                output.flush();
                output.close();
                return false;
            }
        }
        return true;
    }
}
