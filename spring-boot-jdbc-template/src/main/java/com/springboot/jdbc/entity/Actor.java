package com.springboot.jdbc.entity;

import java.util.Date;

/**
 * @Name: Actor
 * @Desc: TODO
 * @User: gxing
 * @Date: 2018-06-25 13:43
 **/
public class Actor {

    private Long ActorId;
    private String firstName;
    private String lastName;
    private Date lastUpdate;

    public Long getActorId() {
        return ActorId;
    }

    public Actor setActorId(Long actorId) {
        ActorId = actorId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Actor setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Actor setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public Actor setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }
}