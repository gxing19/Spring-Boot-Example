package com.gxitsky.service1.impl;

import com.gxitsky.mapper.master.MasterUserMapper;
import com.gxitsky.entity.User;
import com.gxitsky.service1.UserServiceMaster;
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
public class UserServiceMasterImpl implements UserServiceMaster {

    @Autowired
    private MasterUserMapper masterUserMapper;

    @Override
    public List<User> queryAll() {
        return masterUserMapper.selectAll();
    }

    @Override
    public User queryById(int id) {
        return masterUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public User queryByUsername(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        return masterUserMapper.selectOneByExample(example);
    }

    @Override
    public User queryByUsernameXml(String username) {
        return masterUserMapper.queryByUsername(username);
    }

    @Override
    public List<User> queryByPage(User user) {
        List<User> userList = masterUserMapper.select(user);
        return userList;
    }

}