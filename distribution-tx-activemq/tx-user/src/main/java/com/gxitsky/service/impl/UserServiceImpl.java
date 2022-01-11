package com.gxitsky.service.impl;

import com.gxitsky.entity.User;
import com.gxitsky.repository.UserRepository;
import com.gxitsky.service.UserService;
import com.gxitsky.entity.event.EventManager;
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
