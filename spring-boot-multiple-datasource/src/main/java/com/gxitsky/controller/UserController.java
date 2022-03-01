package com.gxitsky.controller;

import com.gxitsky.common.ResultBean;
import com.gxitsky.entity.User;
import com.gxitsky.service1.UserServiceMaster;
import com.gxitsky.service2.UserServiceSlave;
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
    private UserServiceMaster userServiceMaster;

    @Autowired
    private UserServiceSlave userServiceSlave;

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
        List<User> userListOne = userServiceMaster.queryAll();
        List<User> userListTwo = userServiceSlave.queryAll();
        return new ResultBean(userListOne).setSuccessCodeAndState();
    }

    @RequestMapping("/queryByPage")
    public ResultBean queryByPage(User user) {
        //调用通用Mapper方法
        List<User> userListOne = userServiceMaster.queryByPage(user);
        List<User> userListTwo = userServiceSlave.queryByPage(user);
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
        User userOne = userServiceMaster.queryById(id);
        User userTwo = userServiceSlave.queryById(id);
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
        User userOne = userServiceMaster.queryByUsername(username);
        User userTwo = userServiceSlave.queryByUsername(username);
        return new ResultBean(userOne).setSuccessCodeAndState();
    }

    /**
     * @desc: 根据用户名查询, 调用XML
     * @author: gxing
     * @date: 2019/2/19 11:31
     * @param: [username]
     * @return: com.springboot.template.common.ResultBean
     **/
    @RequestMapping("/queryByUsernameXml")
    public ResultBean queryByUsernameXml(String username) {
        User userOne = userServiceMaster.queryByUsernameXml(username);
        User userTwo = userServiceSlave.queryByUsernameXml(username);
        return new ResultBean(userOne).setSuccessCodeAndState();
    }
}