package com.springboot.example.aop.service.impl;

import com.springboot.example.aop.entity.User;
import com.springboot.example.aop.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public int save(User user) {

        return 1;
    }

    @Override
    public User getById(Long id) {
        User user = new User();
        user.setUsername("admin").setPassword("123456").setState(1).setAddress("LocalHost").setBirthday(LocalDate.now());
        return user;
    }

    @Override
    public List<User> getByPage(User user) {
        user.setUsername("root").setPassword("11223344").setState(0).setAddress("Linux").setBirthday(LocalDate.now());
        List<User> userList = new ArrayList<>();
        userList.add(user);
        return userList;
    }
}
