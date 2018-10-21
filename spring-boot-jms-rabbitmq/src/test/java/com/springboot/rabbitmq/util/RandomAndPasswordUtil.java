package com.springboot.rabbitmq.util;

import org.springframework.util.DigestUtils;

import java.util.HashSet;
import java.util.Set;

/**
 *随机数和秘钥工具类
 */
public class RandomAndPasswordUtil {
    //随机数长度
    private static final int RANDOM_LENGTH_4 = 4;
    private static final int RANDOM_LENGTH_6 = 6;
    //========================= 账号秘钥生成  ============================
    /**
     * @Author: luoshengchao
     * @Desc: 获取随机生成指定长度的随机数
     * @Date: 2018/6/29 0029 15:11
     * @Param: [length]
     * @Return: java.lang.String
     */
    public static String getRandom(int length) {
        Set<String> set = new HashSet<String>();
        while (set.size() < length) {
            java.util.Random r=new java.util.Random();
            int nextInt = r.nextInt(10);
            set.add(nextInt+"");
        }
        String number = "";
        for (String str : set) {
            number += str;
        }
        return number;
    }

    /**
     * 获取生成的秘钥
     * @return
     */
    public static String getPassword(){
        String password = ""; //秘钥
        for(int i=0;i<1;i++){
            password = password+(char) (Math.random ()*26+'A');
        }
        for(int i=0;i<1;i++){
            password = password+(char) (Math.random ()*26+'a');
        }
        String numberTwo = getRandom(RANDOM_LENGTH_4);
        password += numberTwo;
        return password;
    }

    /**
     * @Author: luoshengchao
     * @Desc:
     * @Date: 2018/6/29 15:29
     * @Param: [password, salt]  
     * @Return: java.lang.String  
     */  
    public static String EncoderByMd5(String password,String salt){
        //加密后的字符串
//        String newstr = DigestUtils.md5Hex(password + "@" + salt);
        String newstr = DigestUtils.md5DigestAsHex((password + "@" + salt).getBytes());
        return newstr;
    }

    public static String getSalt() {
        return getRandom(RANDOM_LENGTH_6);
    }

    public static void main(String[] args) {
        System.out.println(getRandom(4));
        System.out.println(getSalt());
        System.out.println(getPassword());
    }
}
