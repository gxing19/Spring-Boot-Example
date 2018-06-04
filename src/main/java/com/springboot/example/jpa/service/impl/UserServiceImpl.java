package com.springboot.example.jpa.service.impl;

import com.springboot.example.jpa.entity.User;
import com.springboot.example.jpa.repository.UserRepository;
import com.springboot.example.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User queyrById(Long id) {
		User user = userRepository.findById(id).get();
		return user;
	}
	
	public User addUser(User user) {
		return userRepository.save(user);
	}

}
