package com.springboot.aop.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    private Integer id;
    private int pageNo = 1;
    private int pageSize = 10;
}
