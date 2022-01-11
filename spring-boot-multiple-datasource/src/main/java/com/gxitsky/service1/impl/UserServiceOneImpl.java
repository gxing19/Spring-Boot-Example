package com.gxitsky.service1.impl;

import com.gxitsky.mapper.source1.UserMapperOne;
import com.gxitsky.entity.User;
import com.gxitsky.service1.UserServiceOne;
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
public class UserServiceOneImpl implements UserServiceOne {

    @Autowired
    private UserMapperOne userMapperOne;

    @Override
    public List<User> queryAll() {
        return userMapperOne.selectAll();
    }

    @Override
    public User queryById(int id) {
        return userMapperOne.selectByPrimaryKey(id);
    }

    @Override
    public User queryByUsername(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        return userMapperOne.selectOneByExample(example);
    }

    @Override
    public User queryByUsernameXml(String username) {
        return userMapperOne.queryByUsername(username);
    }

    @Override
    public List<User> queryByPage(User user) {
        List<User> userList = userMapperOne.select(user);
        return userList;
    }

}