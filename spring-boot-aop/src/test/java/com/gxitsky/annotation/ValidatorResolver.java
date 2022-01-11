package com.gxitsky.annotation;

import java.lang.reflect.Field;

/**
 * 自定义注解解析
 */
public class ValidatorResolver {

    public static <T> boolean validate(T t) throws Exception {
        Field[] fields = t.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            ValidateName validateName = field.getAnnotation(ValidateName.class);
            ValidateAge validateAge = field.getAnnotation(ValidateAge.class);
            if (null != validateName) {
                // 判断该字段是否为字符串
                if (validateName.notNull()) {
                    String str = (String) field.get(t);
                    if ( field.getType().equals(String.class) && (str == null || str.length() <= 0)) {
                        throw new NullPointerException(t.getClass().getName() + " `s attribute " + field.getName() + " must is not null");
                    }
                }
            }

            if (null != validateAge) {
                if ((int) (field.get(t)) > validateAge.max()) {
                    throw new Exception(t.getClass().getName() + " `s attribute " + field.getName() + " must is small than the max");
                }
            }

        }

        return true;
    }
}
