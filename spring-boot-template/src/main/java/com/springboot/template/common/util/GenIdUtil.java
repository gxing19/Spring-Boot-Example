package com.springboot.template.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.UuidUtil;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Random;
import java.util.UUID;

/**
 * 唯一ID生成器
 */
public class GenIdUtil {

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
     *
     * @return
     */
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

    /**
     * 根据当前时间毫秒值加3位随机数生成唯一ID
     *
     * @return
     */
    public static String getUniqueId() {
        long currentTimeMillis = System.currentTimeMillis();
        Random random = new Random();
        String uniqueId = currentTimeMillis + String.format("%03d", random.nextInt(999));
        return uniqueId;
    }

}
