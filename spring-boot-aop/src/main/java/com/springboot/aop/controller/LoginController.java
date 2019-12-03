package com.springboot.aop.controller;

import com.springboot.aop.common.bean.ResultBean;
import com.springboot.aop.common.bean.ResultHelper;
import com.springboot.aop.entity.Account;
import com.springboot.aop.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/login")
    public ResultBean<Object> login(Account account) {
        account = loginService.getAccount(account);
        if(null != account){
            HttpSession session = request.getSession();
            session.setAttribute("auth_user", account);
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
