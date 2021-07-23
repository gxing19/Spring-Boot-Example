package com.springboot.example.aop.service.impl;

import com.springboot.example.aop.service.LoginService;
import com.springboot.example.aop.entity.Account;
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
