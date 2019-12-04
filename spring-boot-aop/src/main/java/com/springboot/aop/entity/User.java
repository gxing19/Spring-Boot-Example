package com.springboot.aop.entity;

import com.springboot.aop.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4024267176811951208L;

    private String username;
    /**
     * 性别：1-男,0-女
     */
    private Integer sex;
    private String address;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate birthday;

}
