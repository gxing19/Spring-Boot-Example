package com.springboot.aop.service;

import com.springboot.aop.entity.User;

import java.util.List;

public interface UserService {
    int save(User user);

    User getById(Long id);

    List<User> getByPage();
}
