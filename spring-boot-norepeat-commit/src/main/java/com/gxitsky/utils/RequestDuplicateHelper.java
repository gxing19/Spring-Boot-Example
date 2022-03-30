package com.gxitsky.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * @author gxing
 * @desc 对请求参数进行MD5做判重处理
 * @date 2022/3/30
 */
@Slf4j
public class RequestDuplicateHelper {

    /**
     * @param reqJSON       请求的参数，这里通常是JSON
     * @param excludeParams 请求参数里面要去除哪些字段再求摘要
     * @return 去除参数的MD5摘要
     */
    public static String requestParamMD5(final String reqJSON, String... excludeParams) {
        String requestParam = reqJSON;

        TreeMap paramTreeMap = JSON.parseObject(requestParam, TreeMap.class);
        if (excludeParams != null) {
            List<String> excludeParamList = Arrays.asList(excludeParams);
            if (!excludeParamList.isEmpty()) {
                for (String excludeParam : excludeParamList) {
                    paramTreeMap.remove(excludeParam);
                }
            }
        }

        String paramTreeMapJSON = JSON.toJSONString(paramTreeMap);
        String md5deDupParam = jdkMD5(paramTreeMapJSON);
        log.debug("md5deDupParam = {}, excludeParams = {} {}", md5deDupParam, Arrays.deepToString(excludeParams), paramTreeMapJSON);
        return md5deDupParam;
    }

    private static String jdkMD5(String src) {
        String res = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] mdBytes = messageDigest.digest(src.getBytes());
            res = DatatypeConverter.printHexBinary(mdBytes);
        } catch (Exception e) {
            log.error("", e);
        }
        return res;
    }
}