package com.qizhidao.ecloud.history.contract.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CopyrightUser {
    /**
     * 表主键
     */
    private Long copyrightUserId;

    /**
     * ips_cr_copyright_t表主键
     */
    private Long copyrightId;

    /**
     * 著作权类型
     */
    private Integer copyrightType;

    /**
     * 姓名/单位名称
     */
    private String name;

    /**
     * erp_country_region表主键
     */
    private Long countryId;

    /**
     * 省份id
     */
    private Integer provinceId;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 署名情况(0=本名,1=别名,2=匿名)
     */
    private String signature;

    /**
     * 证件号码
     */
    private String idNo;

    /**
     * 证件类型
     */
    private Integer idType;

    /**
     * 证件附件
     */
    private String idFilePath;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 最后更新人
     */
    private Long lastUpdateBy;

    /**
     * 最后更新时间
     */
    private Date lastUpdateDate;
}