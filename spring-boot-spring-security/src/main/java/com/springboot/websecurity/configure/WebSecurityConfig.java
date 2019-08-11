package com.springboot.websecurity.configure;

import com.springboot.websecurity.service.CustomUserDetailsService;
import com.springboot.websecurity.service.impl.CustomUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;

/**
 * @Name: WebSecurityConfig
 * @Desc: WebSecurity配置类
 * @User: gxing
 * @Date: 2018-08-29 18:06
 **/
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    /*@Autowired
    private CustomUserDetailsService customUserService;*/


    /**
     * 注入跨域配置
     *
     * @return
     */
    @Bean
    public UrlBasedCorsConfigurationSource corsConfig() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置你要允许的网站域名，如果全允许则设为 *
        config.addAllowedOrigin("*");
        // 如果要限制 HEADER 或 METHOD 请自行更改
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    /**
     * 自定义请求授权
     *
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()                                   //关闭CSRF防护
                .cors().configurationSource(corsConfig())               //添加跨域配置
                .and()
                .authorizeRequests()                                    //开启请求权限配置
//                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .antMatchers("/user/**").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/admin/**", "/userInfo").hasAuthority("ADMIN") //是否授权
                .antMatchers("/user/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/", "/login").permitAll()                     //匹配路径,允许任何人访问(不拦截,放行)
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .anyRequest().authenticated()                               //其它请求,要求登录验证后可访问
                .and()
                .formLogin()
                .loginPage("/login")                //自定义登录
                .defaultSuccessUrl("/index")        //登录成功后跳转url
                .failureUrl("/error")               //登录失败跳转url(无法跳转到/error路径,提示无映射,实际有映射,换其它路径正常)
                .permitAll()
                .and()
                .rememberMe()                   //开启Cookie存储用户信息
                .tokenValiditySeconds(604800)   //Cookie有效期
                .key("myKey")                   //Cookie中的私钥
                .and()
                .logout()                       //注销用户
                .logoutUrl("/logout")           //注销用户url
                .logoutSuccessUrl("/login")     //注解成功后跳转url
                .permitAll()
                .and()
                .httpBasic();
    }

    @Bean
    public CustomUserDetailsService customUserDetailsService() {
        return new CustomUserServiceImpl();
    }

    /**
     * 用户认证方法
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 1. 内置允许通过认证的用户
         * Spring Boot 2.0.x集成的是 Spring Security 5.0.x
         * 对认证的密码需要加密处理，否则会报错
         * */
        /*auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("tom").password(new BCryptPasswordEncoder().encode("123456")).roles("USER")
                .and()
                .withUser("kitty").password(new BCryptPasswordEncoder().encode("112233")).roles("USER");
        */

        /**
         * 2. JDBC用户
         * 默认的密码格式是: {id}encodedPassword, jdbc的用户密码必须以这种格式存储才能正确处理
         * 如 123456 使用 bcrypt 加密后存储的密码: {bcrypt}$2a$10$/.4eK1JTNF9h6jBzPh94ROgdgsj6KBVNAmg3I7pNBx1wWbckq97jG
         * 否则会报错误: There is no PasswordEncoder mapped for the id “null”
         */
        /*auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, true from user where username = ?")
                .authoritiesByUsernameQuery("select username, role from user where username = ?");
        */

        /**
         * 3. 自定义通用用户
         */
        auth.userDetailsService(customUserDetailsService());
//        auth.userDetailsService(customUserService);

    }

    /**
     * 自定义Web安全策略
     *
     * @param webSecurity
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        //设置忽略不拦截路径
        webSecurity.ignoring().antMatchers("/resources/static/**");
    }


}