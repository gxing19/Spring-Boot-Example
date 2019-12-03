package com.qizhidao.ecloud.history.contract.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
@ApiModel(value = "CopyrightSoftwareTestInfo", description = "软件评测基础信息")
public class CopyrightSoftwareTestInfo implements Serializable {
    private static final long serialVersionUID = 5536542745087100687L;

    @ApiModelProperty("软件评测ID")
    private Long softwareTestId;

    @ApiModelProperty("主表主键ID")
    private Long copyrightId;

    @ApiModelProperty("软件名全称")
    private String fullName;

    @ApiModelProperty("版本号")
    private String versionNo;

    @ApiModelProperty("开发单位")
    private String developmentUnit;

    @ApiModelProperty("登记号")
    private String registerNo;

    @ApiModelProperty("软件类别：1=嵌入式软件,2=非嵌入式软件")
    private int softwareType;

    @ApiModelProperty("评测机构")
    private String testOrganization;

    @ApiModelProperty("评测费用")
    private String testFee;

    private Long createdBy;
    private LocalDate creationDate;
    private Long lastUpdatedBy;
    private LocalDate lastUpdateDate;

}
