package com.gxitsky.controller;

import com.gxitsky.common.ResultBean;
import com.gxitsky.common.annotation.NoRepeatCommit;
import com.gxitsky.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @NoRepeatCommit
    @RequestMapping("/add")
    public ResultBean<User> add() {
        User user = new User();
        user.setId(System.currentTimeMillis()).setUsername("admin").setPassword("123456");
        return new ResultBean<>(user);
    }
}
