package com.springboot.template.service2.impl;

import com.springboot.template.entity.User;
import com.springboot.template.mapper.source2.UserMapperTwo;
import com.springboot.template.service2.UserServiceTwo;
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
public class UserServiceTwoImpl implements UserServiceTwo {

    @Autowired
    private UserMapperTwo userMapperTwo;

    @Override
    public List<User> queryAll() {
        return userMapperTwo.selectAll();
    }

    @Override
    public User queryById(int id) {
        return userMapperTwo.selectByPrimaryKey(id);
    }

    @Override
    public User queryByUsername(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        return userMapperTwo.selectOneByExample(example);
    }

    @Override
    public User queryByUsernameXml(String username) {
        return userMapperTwo.queryByUsername(username);
    }

    @Override
    public List<User> queryByPage(User user) {
        return null;
    }
}