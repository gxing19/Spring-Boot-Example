package com.springboot.aop.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    private int pageNo = 1;
    private int pageSize = 10;
}
