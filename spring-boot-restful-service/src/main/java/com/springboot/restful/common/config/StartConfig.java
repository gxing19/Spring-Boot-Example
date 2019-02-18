package com.springboot.restful.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class StartConfig {

    private static Logger logger = LoggerFactory.getLogger(StartConfig.class);

    public StartConfig(String[] args) {
        Boolean isServerPort = false;
        String serverPort = "";
        if(args != null){
            for (String arg : args) {
                if(StringUtils.hasText(arg) && arg.startsWith("--server.port")){
                    isServerPort = true;
                    serverPort = arg;
                    break;
                }
            }
        }
        if(!isServerPort){
            int port = ServerPortUtil.getAvailablePort();
            logger.info("current server.port=" + port);
            System.setProperty("server.port",String.valueOf(port));
        }else {
            logger.info("current server.port=" + serverPort.split("=")[1]);
            System.setProperty("server.port", serverPort.split("=")[1]);
        }

    }

}
