package com.gxitsky.controller;

import com.gxitsky.service.LoginService;
import com.gxitsky.common.bean.ResultBean;
import com.gxitsky.common.bean.ResultHelper;
import com.gxitsky.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/login")
    public ResultBean<Object> login(Account account) {
        account = loginService.getAccount(account);
        if (null != account) {
            HttpSession session = request.getSession();
            session.setAttribute("auth_user", account);
//            session.setAttribute("token:" + account.getId(), System.currentTimeMillis());
//            String key = "token:" + account.getId();
//            redisTemplate.opsForValue().setIfAbsent(key, System.currentTimeMillis(), 3600L, TimeUnit.SECONDS);
            return ResultHelper.success();
        }
        return ResultHelper.fail();
    }

    @RequestMapping("/logout")
    public ResultBean<Object> logout() {
        HttpSession session = request.getSession();
        session.setAttribute("auth_user", null);
        return ResultHelper.success();
    }

}
