package com.qizhidao.ecloud.history.contract.entity;

import cn.hutool.core.util.ObjectUtil;

/**
 * ClassName:com.qizhidao.ecloud.enums.ServiceUrgetEnum
 * Description: 加急状态枚举类
 * <p>
 * Date: 2019/6/5 16:31
 *
 * @version 1.0.0
 * @author: 廖亮
 */
public enum ServiceUrgetEnum {

    /**
     * 判断是否加急
     */
    PJ("评测加急"), YJ("软著加急"),QJ("软著其它加急"),ZP("作品加急"),ZQ("作品其它加急"), PT("普通");

    private String value;


    ServiceUrgetEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getServiceIsUrgent(String keyName, String valueName){
        ServiceUrgetEnum[]  urgetEnums = {PJ,YJ,QJ,ZP,ZQ};
        String isUrgent = "N";
        for (ServiceUrgetEnum urgetEnum : urgetEnums) {
            // 服务产品的规格无“评测加急”，“软著加急”或“软著其它加急”，则加急状态为“不加急”，不显示加急时间
           if (!urgetEnum.getValue().equals(keyName)){
               isUrgent= "N";
           }
           // 如有“评测加急”，“软著加急”或“软著其它加急”，当其规格参数为“普通”时，加急状态为“不加急”不显示加急时间，
           else if (urgetEnum.getValue().equals(keyName) && PT.getValue().equals(valueName)){
               isUrgent= "N";
           }else {
               return "Y";
           }
        }
        return isUrgent;
    }

    public static Boolean checkValue(String value){
        if (ObjectUtil.isNull(value)){
            return Boolean.FALSE;
        }
        ServiceUrgetEnum [] enums = {PJ,YJ,QJ,ZP,ZQ};
        for (ServiceUrgetEnum anEnum : enums) {
            if (value.equals(anEnum.getValue())){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
