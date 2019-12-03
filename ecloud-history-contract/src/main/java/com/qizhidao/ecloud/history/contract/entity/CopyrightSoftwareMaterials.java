package com.qizhidao.ecloud.history.contract.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CopyrightSoftwareMaterials {
    /**
     *   表主键
     */
    private Long materialsId;

    /**
     *   ips_cr_copyright_t表主键
     */
    private Long copyrightId;

    /**
     *   材料类型(1=一般交存,2=例外交存)
     */
    private Integer materialsType;

    /**
     *   文档类型(1=一种文档,2=多种文档)
     */
    private Integer docType;

    /**
     *   例外交存(1=使用黑色宽斜线覆盖，页码为,2=前10页和任何连续的50页,3=目标程序的连续前、后各30页和源程序任选连续的20页)
     */
    private Integer exceptionType;

    /**
     *   说明
     */
    private String description;

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
}