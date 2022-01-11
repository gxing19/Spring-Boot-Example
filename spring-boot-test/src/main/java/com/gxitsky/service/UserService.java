package com.gxitsky.service;

import com.gxitsky.entity.User;

public interface UserService {

    public User queyrById(Long id);

    User addUser(User user);
}
