package com.gxitsky.service2;

import com.gxitsky.entity.User;

import java.util.List;

/**
 * @name: UserService
 * @desc: 用户业务接口
 * @author: gxing
 * @date: 2018-09-25 16:04
 **/
public interface UserServiceSlave {

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
     * @date: 2019/2/19 11:27
     * @param: [id]
     * @return: com.springboot.template.entity.User
     **/
    User queryById(int id);

    /**
     * @desc: 根据用户名查询
     * @author: gxing
     * @date: 2019/2/19 11:31
     * @param: [username]
     * @return: com.springboot.template.entity.User
     **/
    User queryByUsername(String username);

    /**
     * @desc: 根据用户名查询, 调用XML
     * @author: gxing
     * @date: 2019/2/19 11:38
     * @param: [username]
     * @return: com.springboot.template.entity.User
     **/
    User queryByUsernameXml(String username);

    List<User> queryByPage(User user);
}
