package com.springboot.example.aop.service;

import com.springboot.example.aop.entity.Account;

/**
 * 登录业务接口
 */
public interface LoginService {
    Account getAccount(Account account);
}
