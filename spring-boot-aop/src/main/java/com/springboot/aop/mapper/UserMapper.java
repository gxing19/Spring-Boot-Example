package com.springboot.aop.mapper;

import com.springboot.aop.entity.User;
import com.springboot.aop.mapper.base.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
