package com.qizhidao.ecloud.history.contract.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CopyrightSoftwareTechnology {
    /**
     *   表主键
     */
    private Long technologyId;

    /**
     *   ips_cr_copyright_t表主键
     */
    private Long copyrightId;

    /**
     *   硬件环境
     */
    private String hardware;

    /**
     *   软件环境
     */
    private String software;

    /**
     *   编程语言
     */
    private String programLanguage;

    /**
     *   源程序量
     */
    private String sourceProgram;

    /**
     *   创建人
     */
    private Long createBy;

    /**
     *   创建时间
     */
    private Date createDate;

    /**
     *   更新人
     */
    private Long lastUpdateBy;

    /**
     *   最后更新时间
     */
    private Date lastUpdateDate;

    /**
     *   主要功能和技术特点
     */
    private String description;
}