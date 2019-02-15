package com.springboot.template.entity.base;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @name: BaseEntity
 * @desc: 实体类的基类, 设置默认分页参数,
 * 分页插件配置了自动从方法参数中获取分页参数,
 * 可省略:PageHelper.startPage(pageNum, pageSize)设置
 * @author: gxing
 * @date: 2018-09-25 11:48
 **/
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 4861768674815629330L;

    /**/

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Transient
    private Integer pageNum = 1;

    @Transient
    private Integer pageSize = 10;

    public Long getId() {
        return id;
    }

    public BaseEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public BaseEntity setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public BaseEntity setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}