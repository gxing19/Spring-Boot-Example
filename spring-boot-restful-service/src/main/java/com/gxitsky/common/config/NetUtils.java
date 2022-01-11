package com.gxitsky.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.Socket;

public class NetUtils {

    private static Logger logger = LoggerFactory.getLogger(NetUtils.class);

    public static boolean isLoclePortUsing(int port) {
        boolean flag = true;
        try {
            flag = isPortUsing("127.0.0.1", port);
        } catch (Exception e) {
            logger.error("error:", e.getMessage());
        }
        return flag;
    }

    public static boolean isPortUsing(String host, int port) {
        boolean flag = false;
        try {
            InetAddress theAddress = InetAddress.getByName(host);
            // 如果该本地端口创建 Socket 成功,说明该端口已被使用; 而实际需要未被使用的端口,给未被使用的端口创建 Socket 会报连接被拒绝的错误
            Socket socket = new Socket(theAddress, port);
            flag = true;
        } catch (Exception e) {
            logger.error("error:", e.getMessage());
        }
        return flag;
    }
}
