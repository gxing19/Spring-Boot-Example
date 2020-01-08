package com.springboot.norepeat.commit.controller;

import com.springboot.norepeat.commit.common.ResultBean;
import com.springboot.norepeat.commit.common.annotation.NoRepeatCommit;
import com.springboot.norepeat.commit.entity.User;
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
