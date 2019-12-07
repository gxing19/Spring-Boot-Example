package com.springboot.aop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.aop.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actor extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -3301670278502438921L;

    private Long actorId;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate;

}
