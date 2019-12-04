package com.springboot.aop.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.aop.common.annotation.NoRepeatCommit;
import com.springboot.aop.common.annotation.RequestLimit;
import com.springboot.aop.common.bean.ResultBean;
import com.springboot.aop.common.bean.ResultHelper;
import com.springboot.aop.common.constant.Constant;
import com.springboot.aop.entity.User;
import com.springboot.aop.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@NoRepeatCommit
@RestController
@RequestMapping("/user")
@RequestLimit(needLogin = true, count = 8, second = 1)
public class UserController {
    private Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private UserService userService;

    @RequestLimit(needLogin = true, count = 5, second = 1)
    @RequestMapping("/getUser/{id}")
    public ResultBean<User> getUser(@PathVariable Integer id) {
        User user = new User("张飞", 1, "深圳", "13622223333", LocalDate.now());
        user.setId(id);
        return ResultHelper.success(user);
    }

    @NoRepeatCommit
    @RequestMapping("/getUser")
    public ResultBean<User> getUser(User user) {
        user.setUsername("刘备").setSex(1).setAddress("湖北").setPhone("13622224444").setBirthday(LocalDate.now());
        return ResultHelper.success(user);
    }

    @NoRepeatCommit
    @PostMapping("/save")
    public ResultBean<User> saveUser(User user) {
        logger.info("保存用户信息:{}", JSON.toJSONString(user));
        //执行保存
        userService.save(user);
        return ResultHelper.success(user);
    }

}
