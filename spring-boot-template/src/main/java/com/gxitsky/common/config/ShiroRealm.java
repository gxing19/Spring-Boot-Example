package com.gxitsky.common.config;

import com.gxitsky.common.constant.ParamConst;
import com.gxitsky.entity.User;
import com.gxitsky.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LogManager.getLogger(ShiroRealm.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("开始认证用户身份 .....");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        List<User> userList = userMapper.select(new User(usernamePasswordToken.getUsername()));

        if (null == userList || userList.isEmpty()) {
            logger.info("用户名不存在:{}", usernamePasswordToken.getUsername());
            throw new AccountException("帐号或密码不正确");
        }

        User user = userList.get(0);

        if (!user.getState().equals(ParamConst.ENABLE)) {
            logger.info("用户账号被禁用:{}", usernamePasswordToken.getUsername());
            throw new AccountException("账号已被禁用");
        }

        String password = new String(usernamePasswordToken.getPassword());
        String encryPwd = new Md5Hash(password, ParamConst.PWD_SALT, 2).toString();

        if (!user.getPassword().equals(encryPwd)) {
            logger.info("密码错误:{}", password);
            throw new AccountException("帐号或密码错误");
        }
        SecurityUtils.getSubject().getSession().setAttribute("user", user);
        logger.info("用户身份认证成功:{}", usernamePasswordToken.getUsername());
        return new SimpleAuthenticationInfo(user, password, getName());
    }

}
