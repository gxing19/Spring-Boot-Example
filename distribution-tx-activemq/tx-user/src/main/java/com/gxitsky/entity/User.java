package com.gxitsky.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Accessors(chain = true)
@Table("t_user")
public class User {

    @Id
    private Long id;
    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, Date createTime) {
        this.userName = userName;
        this.createTime = createTime;
    }
}
