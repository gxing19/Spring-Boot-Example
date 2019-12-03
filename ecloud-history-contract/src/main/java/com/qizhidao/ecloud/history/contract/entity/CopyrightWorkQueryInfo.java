package com.qizhidao.ecloud.history.contract.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(value = "CopyrightWorkQueryInfo", description = "作品版权查询-基本信息表")
public class CopyrightWorkQueryInfo {
    /**
     * 表主键
     */
    @ApiModelProperty(value = "作品版权查询ID")
    private Long workQueryId;

    /**
     * ips_cr_copyright_t表主键
     */
    @ApiModelProperty(value = "版权ID")
    private Long copyrightId;

    /**
     * 作品名称
     */
    @ApiModelProperty(value = "作品名称")
    private String workName;

    /**
     * 登记号
     */
    @ApiModelProperty(value = "登记号")
    private String registerNo;

    /**
     * 著作权人
     */
    @ApiModelProperty(value = "著作权人")
    private String copyrightOwner;

    /**
     * 登记申请流水号
     */
    @ApiModelProperty(value = "登记申请流水号")
    private String registerApplyNo;

    /**
     * 查询人性质:1作品登记人,2司法机关或行政管理机关
     */
    @ApiModelProperty(value = "查询人性质:1作品登记人,2司法机关或行政管理机关")
    private String queryType;

    /**
     * 1作品登记人[1质权登记2质权注销3其他],2司法机关或行政管理机关[1司法、行政机关调查取证2其他]
     */
    @ApiModelProperty(value = "1作品登记人[1质权登记2质权注销3其他],2司法机关或行政管理机关[1司法、行政机关调查取证2其他]")
    private String queryPurpose;

    /**
     * 其他说明
     */
    @ApiModelProperty(value = "其他说明")
    private String otherInfo;

    /**
     * 调档要求：1不调取档案、2调取全部档案、3调取XXX部分档案
     */
    @ApiModelProperty(value = "调档要求：1不调取档案、2调取全部档案、3调取XXX部分档案")
    private Integer queryRequirement;

    /**
     * query_requirement=2时替换XXX
     */
    @ApiModelProperty(value = "query_requirement=2时替换XXX")
    private String queryRequirementDesc;

    /**
     * 查询结果领取方式:1邮寄，2自取
     */
    @ApiModelProperty(value = "查询结果领取方式:1邮寄，2自取")
    private Integer resultReceiveWay;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date creationDate;

    /**
     * 最后更新人
     */
    private Long lastUpdatedBy;

    /**
     * 修改时间
     */
    private Date lastUpdateDate;

    /**
     * 作者
     */
    private String author;

    /**
     * 原登记日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate oldRegisterDate;
}