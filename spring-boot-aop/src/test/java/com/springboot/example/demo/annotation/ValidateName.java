package com.springboot.example.demo.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateName {

    /**
     * if is true, the field is null throw exception
     * @return
     */
    boolean notNull();

}
