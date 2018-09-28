package com.springboot.template.service.impl;

import com.springboot.template.entity.User;
import com.springboot.template.mapper.UserMapper;
import com.springboot.template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @name: UserServiceImpl
 * @desc: 用户业务实现
 * @author: gxing
 * @date: 2018-09-25 16:05
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAll() {
        return userMapper.selectAll();
    }

    @Override
    public User queryByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User queryByUsername(String username) {
        return userMapper.queryByUsername(username);
    }

    @Override
    public List<User> queryByPage(User user) {
        //实体类继承默认的分页参数,可不用设置
//        PageHelper.startPage(user.getPage(), user.getRows());
//        PageHelper.startPage(5, 8);
        return userMapper.queryByPage(user);
    }

    @Override
    public User queryById(Integer id) {
        return userMapper.queryById(id);
    }

    @Override
    public List<User> queryByUser(User user) {
        //select()方法:非空等号比较
        List<User> userList1 = userMapper.select(user);

        //构建example来查条件查询
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("username", user.getUsername())
                .orEqualTo("id", user.getId());
        List<User> userList2 = userMapper.selectByExample(example);
        return userList1;
    }

}