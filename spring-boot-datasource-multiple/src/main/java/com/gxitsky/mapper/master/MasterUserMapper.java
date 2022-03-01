package com.gxitsky.mapper.master;

import com.gxitsky.entity.User;
import com.gxitsky.mapper.base.BaseMapper;
import org.springframework.stereotype.Repository;


/**
 * @name: UserMapper
 * @desc: 继承自定义的基础接口Mapper<T>
 * @author: gxing
 * @date: 2018-09-25 15:56
 **/
@Repository
public interface MasterUserMapper extends BaseMapper<User> {

    /**
     * @desc: 根据用户名查询
     * @author: gxing
     * @date: 2018/9/25 16:21
     * @param: [username]
     * @return: com.springboot.template.entity.User
     **/
    User queryByUsername(String username);
}