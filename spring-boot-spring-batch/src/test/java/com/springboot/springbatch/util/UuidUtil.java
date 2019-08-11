package com.springboot.springbatch.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.UUID;

/**
 * 为了避免uuid重复特地加上mac地址hash后的值
 * Created by lutao on 2018/1/8.
 */
public class UuidUtil {

    private static Logger log = LogManager.getLogger(UuidUtil.class);
    private static String HASH_MAC = "";

    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};


    /**
     * 生成短uuid
     * 短8位UUID思想其实借鉴微博短域名的生成方式，但是其重复概率过高，而且每次生成4个，需要随即选取一个。
     * 本算法利用62个可打印字符，通过随机生成32位UUID，由于UUID都为十六进制，所以将UUID分成8组，每4个为一组，
     * 然后通过模62操作，结果作为索引取出字符，
     *
     * @return
     */
    @Test
    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     * 返回一个混合了mac地址的uuid
     *
     * @return
     */
    public static String getUuidMixMac() {

        if ("".equals(HASH_MAC)) {
            try {
                HASH_MAC = getMACAddress().hashCode() + "";
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        String s = UUID.randomUUID().toString();
        return (HASH_MAC + UUID.randomUUID().toString()).replace("-", "");

    }

    /**
     * 获取本机mac地址
     *
     * @return
     * @throws Exception
     */
    public static String getMACAddress() throws Exception {
        // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
        byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
        // 下面代码是把mac地址拼装成String
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            // mac[i] & 0xFF 是为了把byte转化为正整数
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length() == 1 ? 0 + s : s);
        }
        // 把字符串所有小写字母改为大写成为正规的mac地址并返回
        return sb.toString().toUpperCase();
    }

    public static void main(String[] arg) throws Exception {

        System.out.println(getMACAddress());
        System.out.println(getUuidMixMac());
        System.out.println(generateShortUuid());
    }

}
