package com.gxitsky.controller;

import com.gxitsky.common.bean.ResultBean;
import com.gxitsky.common.bean.ResultHelper;
import com.gxitsky.entity.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/token")
public class TokenController {
    private static final Logger logger = LogManager.getLogger(TokenController.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/noRepeatToken")
    public ResultBean genNoRepeatToken(HttpServletRequest request, HttpServletResponse response) {
        logger.info("/token/noRepeatToken -> genNoRepeatToken()");

        Account account = (Account) request.getSession().getAttribute("auth_user");
        String value = request.getRequestedSessionId() + ":" + account.getId() + ":" + System.currentTimeMillis();
        String token = DigestUtils.md5DigestAsHex(value.getBytes(Charset.defaultCharset()));

        HttpSession session = request.getSession();
        session.setAttribute("NO-REPEAT", token);

        Map<String,String> map = new HashMap<>();
        map.put("NO-REPEAT", token);

        return ResultHelper.success(map);
    }

}
