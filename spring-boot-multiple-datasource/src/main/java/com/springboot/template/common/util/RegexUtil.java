package com.springboot.template.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: RegexUtil
 * @Description: 正则工具类
 * @User: gxing
 * @Date: 2018-05-11 13:47
 **/
public class RegexUtil {
    //email正则
    private static final String EMAIL_REGEX = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    //手机号正则
    private static final String MOBILE_NO_REGEX = "^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})$";
    //18位身份证号正则
    private static final String ID_CARD_NO_REGEX = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)";


    /**
     * @Author: gxing
     * @Desc: 检查Email地址
     * @Date: 2018/5/11 13:47
     * @Param: []
     * @Return: java.lang.Boolean
     **/
    public static boolean checkEmail(String email) {
        return executeRegex(email, EMAIL_REGEX);
    }

    /**
     * @Author: gxing
     * @Desc: 检查手机号码
     * @Date: 2018/5/11 13:49
     * @Param: []
     * @Return: void
     **/
    public static boolean checkMobileNo(String mobileNo) {
        return executeRegex(mobileNo, MOBILE_NO_REGEX);
    }

    /**
     * @Author: gxing
     * @Desc: 检查身份证号码
     * @Date: 2018/5/11 15:12
     * @Param: [idCardNo]
     * @Return: boolean
     **/
    public static boolean checkIdCardNo(String idCardNo) {
        return executeRegex(idCardNo, ID_CARD_NO_REGEX);
    }

    /**
     * @Author: gxing
     * @Desc: 执行正则校验
     * @Date: 2018/5/11 14:44
     * @Param: [mobileNo, mobileNoRegex]
     * @Return: boolean
     **/
    private static boolean executeRegex(String str, String regex) {
        boolean flag = false;
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}