package com.springboot.template.service;

import com.springboot.template.entity.User;

import java.util.List;

/**
 * @name: UserService
 * @desc: 用户业务接口
 * @author: gxing
 * @date: 2018-09-25 16:04
 **/
public interface UserService {

    /**
     * @desc: 查询所有
     * @author: gxing
     * @date: 2018/9/25 16:05
     * @param: []
     * @return: java.util.List<com.springboot.template.entity.User>
     **/
    List<User> queryAll();

    /**
     * @desc: 根据主键ID查询
     * @author: gxing
     * @date: 2018/9/25 16:14
     * @param: [id]
     * @return: com.springboot.template.entity.User
     **/
    User queryByPrimaryKey(Integer id);

    /**
     * @desc: 根据用户名查询
     * @author: gxing
     * @date: 2018/9/25 16:20
     * @param: [username]
     * @return: com.springboot.template.entity.User
     **/
    User queryByUsername(String username);

    /**
     * @desc: 分页查
     * @author: gxing
     * @date: 2018/9/25 18:27
     * @param: [user]
     * @return: java.util.List<com.springboot.template.entity.User>
     **/
    List<User> queryByPage(User user);

    /**
     * @desc: 根据ID查询
     * @author: gxing
     * @date: 2018/9/26 9:38
     * @param: [id]
     * @return: com.springboot.template.entity.User
     **/
    User queryById(Integer id);

    /**
     * @desc: 多条件查询
     * @author: gxing
     * @date: 2018/9/26 14:03
     * @param: [user]
     * @return: java.util.List<com.springboot.template.entity.User>
     **/
    List<User> queryByUser(User user);

    /**
     * @desc: 添加用户
     * @author: gxing
     * @date: 2018/9/26 9:38
     * @param: [id]
     * @return: com.springboot.template.entity.User
     **/
    void addUser(User user);
}
