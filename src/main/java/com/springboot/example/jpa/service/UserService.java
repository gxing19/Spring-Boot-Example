package com.springboot.example.jpa.service;

import com.springboot.example.jpa.entity.User;

public interface UserService {

	public User queyrById(Long id);

	User addUser(User user);
}
