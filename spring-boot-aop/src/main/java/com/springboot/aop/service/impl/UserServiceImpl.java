package com.springboot.aop.service.impl;

import com.springboot.aop.entity.User;
import com.springboot.aop.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public int save(User user) {
        //执行保存
        return 1;
    }
}
