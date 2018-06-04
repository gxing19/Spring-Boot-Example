package com.springboot.example.mybatis.service.impl;

import com.springboot.example.mybatis.entity.User;
import com.springboot.example.mybatis.mapper.UserMapper;
import com.springboot.example.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gxing
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public int addUser(User user) {
        return 0;
    }
}
