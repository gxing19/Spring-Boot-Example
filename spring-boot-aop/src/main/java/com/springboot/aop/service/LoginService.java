package com.springboot.aop.service;

import com.springboot.aop.entity.Account;

/**
 * 登录业务接口
 */
public interface LoginService {
    Account getAccount(Account account);
}
