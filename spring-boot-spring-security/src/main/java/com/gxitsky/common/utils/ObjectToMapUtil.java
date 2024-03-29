package com.gxitsky.common.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Name: ObjectToMap
 * @Desc: TODO
 * @User: gxing
 * @Date: 2018-09-05 18:25
 **/
public class ObjectToMapUtil {

    public static Map<String, String> obj2Map(Object obj) {

        if (obj == null) {
            return null;
        }

        Map<String, String> map = new HashMap<>();

        Field[] fields = obj.getClass().getDeclaredFields();
        /*
        //扁历对象属性
        for (Field field : fields) {
            //私有属性设置可访问
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }*/

        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            varName = varName.toLowerCase();//将key置为小写，默认为对象的属性
            try {
                // 获取原来的访问控制权限
                boolean accessFlag = fields[i].isAccessible();
                // 修改访问控制权限
                fields[i].setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量
                Object o = fields[i].get(obj);
                if (o != null)
                    map.put(varName, o.toString());
                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);
                // 恢复访问控制权限
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return map;
    }
}