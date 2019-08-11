package com.springboot.jpa.service;

import com.springboot.jpa.entity.User;

public interface UserService {

    public User queyrById(Long id);

    User addUser(User user);
}
