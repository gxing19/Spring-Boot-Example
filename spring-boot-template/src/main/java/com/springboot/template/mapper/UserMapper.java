package com.springboot.template.mapper;

import com.springboot.template.entity.User;
import com.springboot.template.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @name: UserMapper
 * @desc: 继承自定义的基础接口Mapper<T>
 * @author: gxing
 * @date: 2018-09-25 15:56
 **/
@Repository
@CacheNamespaceRef(UserMapper.class)
public interface UserMapper extends BaseMapper<User> {

    /**
     * @desc: 根据用户名查询
     * @author: gxing
     * @date: 2018/9/25 16:21
     * @param: [username]
     * @return: com.springboot.template.entity.User
     **/
    User queryByUsername(String username);

    /**
     * @desc: 分页查询
     * @author: gxing
     * @date: 2018/9/25 18:35
     * @param: [user]
     * @return: java.util.List<com.springboot.template.entity.User>
     **/
    List<User> queryByPage(User user);

    /**
     * @desc: 使用SQL注解
     * @author: gxing
     * @date: 2018/9/25 19:02
     * @param: [id]
     * @return: com.springboot.template.entity.User
     **/
    @Select(value = "select * from user where id = #{id}")
    User queryById(@Param("id") Long id);
}

