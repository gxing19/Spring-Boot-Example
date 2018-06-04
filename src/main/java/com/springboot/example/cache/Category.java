package com.springboot.example.cache;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Category {

    @Id
    private Long CategoryId;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdate;

    public Long getCategoryId() {
        return CategoryId;
    }

    public Category setCategoryId(Long categoryId) {
        CategoryId = categoryId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public Category setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }
}
