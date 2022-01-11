package com.gxitsky.service;

import com.gxitsky.entity.User;

import java.util.List;

public interface UserService {
    int save(User user);

    User getById(Long id);

    List<User> getByPage(User user);
}
