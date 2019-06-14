package com.springboot.nosql.config.sequence;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * @name: Student
 * @desc: TODO
 * @author: gxing
 * @date: 2019-06-13 09:50
 **/
@Document
public class Student {

    @GeneratedValue
    @Id
    private long id;

    private String name;

    public Student() {
    }

    public Student(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public Student setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }
}