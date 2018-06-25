package com.springboot.jdbc.service;

import com.springboot.jdbc.entity.Actor;
import com.springboot.jdbc.entity.User;

import java.util.List;

public interface UserService {

    int addUser(User user);

    int updateUser(String name, Long id);

    int deleteUser(Long id);

    Actor queryByActorId(Long actorId);

    int queryCount();

    int queryCountByLastName(String lastName);

    String queryLastName(Long actorId);

    List<Actor> queryActorList();
}
