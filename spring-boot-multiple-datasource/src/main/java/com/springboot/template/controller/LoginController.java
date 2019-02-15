package com.springboot.template.controller;

import com.springboot.template.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @name: LoginController
 * @desc: 登录登出注销
 * @author: gxing
 * @date: 2018-09-27 14:32
 **/
@Controller
public class LoginController {

    /**
     * @desc: 登录
     * @author: gxing
     * @date: 2018/9/27 14:33
     * @param: [user]
     * @return: java.lang.String
     **/
    @PostMapping("/doLogin")
    public String doLogin(User user) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUsername(),user.getPassword());
        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            subject.login(usernamePasswordToken);
        } catch (Exception e) {
            return "login";
        }
        return "redirect:/index";
    }
}