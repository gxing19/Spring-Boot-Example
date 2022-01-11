package com.gxitsky.service.impl;

import com.gxitsky.repository.SysUserRepository;
import com.gxitsky.entity.SysUser;
import com.gxitsky.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Name: CustomUserServiceImpl
 * @Desc: 用户业务处理
 * @User: gxing
 * @Date: 2018-09-04 17:02
 **/
@Service
public class CustomUserServiceImpl implements CustomUserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Example<SysUser> example = Example.of(new SysUser().setUsername(username));
        SysUser sysUser = sysUserRepository.findOne(example).get();

//        List<GrantedAuthority> authorityList = new ArrayList<>();
//        authorityList.add(new SimpleGrantedAuthority(sysUser.getRole()));
//        return new User(sysUser.getUsername(), sysUser.getPassword(),authorityList);
        return sysUser;
    }
}