package com.springboot.aop.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Account implements Serializable {
    private static final long serialVersionUID = -2337772509104589028L;
    private Long id;
    private String username;
    private String password;
    private String verifyCode;
}
