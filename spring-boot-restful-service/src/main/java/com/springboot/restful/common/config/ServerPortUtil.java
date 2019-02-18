package com.springboot.restful.common.config;


import java.util.Random;

public class ServerPortUtil {

    public static int getAvailablePort() {
        int max = 65535;
        int min = 2000;
        Random random = new Random();
        int nextInt = random.nextInt(max);
        int port = nextInt % (max - min + 1) + min;
        boolean using = NetUtils.isLoclePortUsing(port);
        if (using) {
            return getAvailablePort();
        } else {
            return port;
        }
    }
}
