package com.springboot.template.entity.base;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @name: BaseEntity
 * @desc: 实体类的基类,设置默认分页参数,可省略:PageHelper.startPage(5, 8) 设置
 * @author: gxing
 * @date: 2018-09-25 11:48
 **/
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 4861768674815629330L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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