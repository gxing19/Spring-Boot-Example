package com.gxitsky.controller;

import com.github.pagehelper.PageInfo;
import com.gxitsky.common.ResultBean;
import com.gxitsky.entity.User;
import com.gxitsky.service.UserService;
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
    private UserService userService;

    /**
     * @desc: 查所有
     * @author: gxing
     * @date: 2018/9/25 18:25
     * @param: []
     * @return: com.springboot.template.common.constant.ResultBean
     **/
    @RequestMapping("/queryAll")
    public ResultBean queryAll() {
        List<User> userList = userService.queryAll();
        return new ResultBean(userList).setSuccessCodeAndState();
    }

    /**
     * @desc: 根据ID查
     * @author: gxing
     * @date: 2018/9/25 18:25
     * @param: [id]
     * @return: com.springboot.template.common.constant.ResultBean
     **/
    @RequestMapping("/queryById")
    public ResultBean queryById(Long id) {
        if (null == id) {
            return new ResultBean("ID不能为空").setFailCodeAndState();
        }
        //通用Mapper接口方法
        User user1 = userService.queryByPrimaryKey(id);
        //手写注解式查询
        User user2 = userService.queryById(id);
        return new ResultBean(user1).setSuccessCodeAndState();
    }

    /**
     * @desc: 根据用户名查
     * @author: gxing
     * @date: 2018/9/25 18:26
     * @param: [username]
     * @return: com.springboot.template.entity.User
     **/
    @RequestMapping("/queryByUsername")
    public User queryByUsername(String username) {
        User user = userService.queryByUsername(username);
        return user;
    }

    /**
     * @desc: 分页查
     * @author: gxing
     * @date: 2018/9/25 18:27
     * @param: [user]
     * @return: com.springboot.template.entity.User
     **/
    @RequestMapping("/queryByPage")
    public ResultBean queryByPage(User user) {
        //实体类继承基类默认的分页参数,可不用设置
//        PageHelper.startPage(user.getPageNum(), user.getPageSize());
        List<User> userList = userService.queryByPage(user);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return new ResultBean(userPageInfo).setSuccessCodeAndState();
    }

    /**
     * @desc: 多条件查询
     * @author: gxing
     * @date: 2018/9/26 17:04
     * @param: [user]
     * @return: java.util.List<com.springboot.template.entity.User>
     **/
    @RequestMapping("/queryByUser")
    public List<User> queryByUser(User user) {
        List<User> userList = userService.queryByUser(user);
        return userList;
    }

    @RequestMapping("/updateUserMoney")
    public void updateUserMoney() throws InterruptedException {
        userService.updateUserMoney();
    }
}