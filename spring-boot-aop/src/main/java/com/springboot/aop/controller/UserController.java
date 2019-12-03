package com.springboot.aop.controller;

import com.springboot.aop.common.annotation.NoRepeatCommit;
import com.springboot.aop.common.annotation.RequestLimit;
import com.springboot.aop.common.bean.ResultBean;
import com.springboot.aop.common.bean.ResultHelper;
import com.springboot.aop.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/user")
@RequestLimit(needLogin = true, count = 8, second = 1)
public class UserController {

    @RequestLimit(needLogin = true, count = 5, second = 1)
    @RequestMapping("/getUser/{id}")
    public ResultBean<User> getUser(@PathVariable Integer id){

        User user = new User("张飞", 1, "深圳", "13822223333", LocalDate.now());
        user.setId(id);
        return ResultHelper.success(user);
    }

    @NoRepeatCommit
    @RequestMapping("/getUser")
    public ResultBean<User> getUser(User user){
        user.setUsername("刘备").setSex(1).setAddress("湖北").setPhone("13020002000").setBirthday(LocalDate.now());
        return ResultHelper.success(user);
    }

}
