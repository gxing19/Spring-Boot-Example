package com.springboot.example.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.example.mybatis.entity.User;
import com.springboot.example.mybatis.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user",method = RequestMethod.POST)
public class UserController {
    private final static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/query")
    public User addUser(User user){
        user.setAddress("宝安").setAge(11).setName("Rose");
        logger.info(JSON.toJSONString(user));
        int i = userService.addUser(user);
        return user;
    }
}
