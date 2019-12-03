package com.qizhidao.ecloud.history.contract.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(value = "CopyrightTransferDetail", description = "软著版权转让许可-详情信息")
public class CopyrightTransferDetail {
    /**
     *   表主键
     */
    @ApiModelProperty(value = "表主键")
    private Long id;

    /**
     *   ips_cr_copyright_t表主键
     */
    @ApiModelProperty(value = "转让信息ID")
    private Long copyrightTransferId;

    /**
     *   ips_cr_copyright_t表主键
     */
    @ApiModelProperty(value = "版权ID")
    private Long copyrightId;

    /**
     *   软件全称
     */
    @ApiModelProperty(value = "软件全称")
    private String fullName;

    /**
     *   1许可方信息,2受让方信息
     */
    @ApiModelProperty(value = "1许可方信息,2受让方信息")
    private Integer transferType;

    /**
     *   名字
     */
    @ApiModelProperty(value = "名字")
    private String name;

    /**
     *   国籍
     */
    @ApiModelProperty(value = "国籍")
    private String countryCode;
    private String countryName;

    /**
     *   省
     */
    @ApiModelProperty(value = "省")
    private Integer provinceCode;
    private String provinceName;

    /**
     *   市
     */
    @ApiModelProperty(value = "市")
    private Integer cityCode;
    private String cityName;

    /**
     *   区
     */
    @ApiModelProperty(value = "区")
    private Integer regionCode;
    private String regionName;

    /**
     *   地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     *   创建人
     */
    private Long createdBy;

    /**
     *   创建时间
     */
    private Date creationDate;

    /**
     *   最后更新人
     */
    private Long lastUpdatedBy;

    /**
     *   修改时间
     */
    private Date lastUpdateDate;
}