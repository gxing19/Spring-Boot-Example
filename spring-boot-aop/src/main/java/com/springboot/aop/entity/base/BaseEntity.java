package com.springboot.aop.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    private int pageNum = 1;
    private int pageSize = 10;
}
