package com.springboot.norepeat.commit.controller;

import com.springboot.norepeat.commit.common.constant.ConstantParm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping("/noRepeat")
    public String noRepeat() {
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString().replace("-", "").toLowerCase();
        redisTemplate.opsForValue().set(ConstantParm.TOKEN_PRE + ":" + token, "");

        Cookie cookie = new Cookie(ConstantParm.TOKEN_PRE, token);
        response.addCookie(cookie);
        return token;
    }
}
