package com.springboot.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/", "/login").permitAll()      //匹配路径,允许任何人访问(不拦截,放行)
                .anyRequest().authenticated()                //任何请求映射需要身份认证才可访问
                .and()
                .formLogin().loginPage("/login")             //表单认证,定义登录url
                .defaultSuccessUrl("/chat")                  //认证成功后默认跳转访问的url
                .permitAll()
                .and()
                .logout()                                    //退出登录
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //内置允许通过认证的用户, Spring Boot 2.0.x集成的是 Spring Security 5.0.x , 对认证的密码需要加密处理，否则会报错
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("tom").password(new BCryptPasswordEncoder().encode("123456")).roles("USER")
                .and()
                .withUser("kitty").password(new BCryptPasswordEncoder().encode("112233")).roles("USER");
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        //忽略静态资源目录,不拦截
        webSecurity.ignoring().antMatchers("/resources/static/**");
    }

}

