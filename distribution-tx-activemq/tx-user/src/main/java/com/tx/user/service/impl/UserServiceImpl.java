package com.tx.user.service.impl;

import com.tx.user.entity.User;
import com.tx.user.entity.event.EventManager;
import com.tx.user.repository.UserRepository;
import com.tx.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventManager eventManager;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public User addUser(User user) {
        user = userRepository.save(user);
        eventManager.eventHandle(user.getId());
        return user;
    }

}
