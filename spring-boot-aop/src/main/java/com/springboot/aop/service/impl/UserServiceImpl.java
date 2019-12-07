package com.springboot.aop.service.impl;

import com.springboot.aop.entity.User;
import com.springboot.aop.mapper.UserMapper;
import com.springboot.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(User user) {
        //执行保存
        return 1;
    }

    @Override
    public User getById(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public List<User> getByPage() {
        List<User> userList = userMapper.selectAll();
        return userList;
    }
}
