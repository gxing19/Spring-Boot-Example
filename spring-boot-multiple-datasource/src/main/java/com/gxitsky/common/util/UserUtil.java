package com.gxitsky.common.util;

import com.gxitsky.entity.User;
import org.apache.shiro.SecurityUtils;

/**
 * 登录用户工具类
 *
 * @author zhangweizhong
 */
public class UserUtil {

    /**
     * 获取用户ID
     *
     * @return
     */
    public static Long getUserId() {
        return getUser().getId();
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static User getUser() {
        return (User) SecurityUtils.getSubject().getSession().getAttribute("user");
    }

}
