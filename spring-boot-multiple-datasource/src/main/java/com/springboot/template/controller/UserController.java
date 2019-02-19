package com.springboot.template.controller;

import com.springboot.template.common.ResultBean;
import com.springboot.template.entity.User;
import com.springboot.template.service1.UserServiceOne;
import com.springboot.template.service2.UserServiceTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @name: UserController
 * @desc: 用户Controller
 * @author: gxing
 * @date: 2018-09-25 16:01
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceOne userServiceOne;

    @Autowired
    private UserServiceTwo userServiceTwo;

    /**
     * @desc: 查所有
     * @author: gxing
     * @date: 2018/9/25 18:25
     * @param: []
     * @return: com.springboot.template.common.constant.ResultBean
     **/
    @RequestMapping("/queryAll")
    public ResultBean queryAll() {
        //调用通用Mapper方法
        List<User> userListOne = userServiceOne.queryAll();
        List<User> userListTwo = userServiceTwo.queryAll();
        return new ResultBean(userListOne).setSuccessCodeAndState();
    }

    /**
     * @desc: 根据主键ID查询
     * @author: gxing
     * @date: 2019/2/19 11:26
     * @param: [id]
     * @return: com.springboot.template.common.ResultBean
     **/
    @RequestMapping("/queryById")
    public ResultBean queryById(int id) {
        User userOne = userServiceOne.queryById(id);
        User userTwo = userServiceTwo.queryById(id);
        return new ResultBean(userOne).setSuccessCodeAndState();
    }

    /**
     * @desc: 根据用户名查询
     * @author: gxing
     * @date: 2019/2/19 11:31
     * @param: [username]
     * @return: com.springboot.template.common.ResultBean
     **/
    @RequestMapping("/queryByUsername")
    public ResultBean queryByUsername(String username) {
        User userOne = userServiceOne.queryByUsername(username);
        User userTwo = userServiceTwo.queryByUsername(username);
        return new ResultBean(userOne).setSuccessCodeAndState();
    }

    /**
     * @desc: 根据用户名查询,调用XML
     * @author: gxing
     * @date: 2019/2/19 11:31
     * @param: [username]
     * @return: com.springboot.template.common.ResultBean
     **/
    @RequestMapping("/queryByUsernameXml")
    public ResultBean queryByUsernameXml(String username) {
        User userOne = userServiceOne.queryByUsernameXml(username);
        User userTwo = userServiceTwo.queryByUsernameXml(username);
        return new ResultBean(userOne).setSuccessCodeAndState();
    }
}