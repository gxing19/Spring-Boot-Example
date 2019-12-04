package com.springboot.aop.service.impl;

import com.springboot.aop.entity.Account;
import com.springboot.aop.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public Account getAccount(Account account) {

        if (null != account) {
            if(account.getUsername().equals("admin") && account.getPassword().equals("123456")){
                return account.setId(1003L);
            }
        }
        return null;
    }
}
