package com.gxitsky.service2.impl;

import com.gxitsky.mapper.slave.SlaveUserMapper;
import com.gxitsky.entity.User;
import com.gxitsky.service2.UserServiceSlave;
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
public class UserServiceSlaveImpl implements UserServiceSlave {

    @Autowired
    private SlaveUserMapper slaveUserMapper;

    @Override
    public List<User> queryAll() {
        return slaveUserMapper.selectAll();
    }

    @Override
    public User queryById(int id) {
        return slaveUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public User queryByUsername(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        return slaveUserMapper.selectOneByExample(example);
    }

    @Override
    public User queryByUsernameXml(String username) {
        return slaveUserMapper.queryByUsername(username);
    }

    @Override
    public List<User> queryByPage(User user) {
        return null;
    }
}