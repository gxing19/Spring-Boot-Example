package com.springboot.example.aop.service;

import com.springboot.example.aop.entity.User;

import java.util.List;

public interface UserService {
    int save(User user);

    User getById(Long id);

    List<User> getByPage(User user);
}
