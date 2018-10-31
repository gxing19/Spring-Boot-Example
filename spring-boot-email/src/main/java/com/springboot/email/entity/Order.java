package com.springboot.email.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @name: Order
 * @desc: TODO
 * @author: gxing
 * @date: 2018-10-30 11:27
 **/
public class Order implements Serializable {
    private static final long serialVersionUID = -2700458838904795906L;

    private Long id;
    private Long userId;
    private Date createDateTime;

    public Order(Long id, Long userId, Date createDateTime) {
        this.id = id;
        this.userId = userId;
        this.createDateTime = createDateTime;
    }

    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Order setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public Order setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }
}