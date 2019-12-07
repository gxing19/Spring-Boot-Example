package com.springboot.aop.mapper.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @name: BaseMapper
 * @desc: Mapper<T>基础接口,继承Mapper<T>和MySqlMapper<T>
 * @author: gxing
 * @date: 2018-09-27 16:53
 **/
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
