package com.qizhidao.ecloud.history.contract.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(value = "CopyrightQueryInfo", description = "软著版权查询-信息表")
public class CopyrightQueryInfo {
    /**
     *   表主键软著版权查询
     */
    @ApiModelProperty(value = "软著版权查询ID")
    private Long copyrightQueryId;

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
     *   软件简称
     */
    @ApiModelProperty(value = "软件简称")
    private String shortName;

    /**
     *   版本号
     */
    @ApiModelProperty(value = "版本号")
    private String versionNo;

    /**
     *   登记号
     */
    @ApiModelProperty(value = "登记号")
    private String registerNo;

    /**
     *   分类号
     */
    @ApiModelProperty(value = "分类号")
    private String classifyNo;

    /**
     *   登记申请流水号
     */
    @ApiModelProperty(value = "登记申请流水号")
    private String registerApplyNo;

    /**
     *   著作权人
     */
    @ApiModelProperty(value = "著作权人")
    private String copyrightOwner;

    /**
     *   权利取得方式:1原始取得，2寄受取得，3承受取得，4其他
     */
    @ApiModelProperty(value = "权利取得方式:1原始取得，2寄受取得，3承受取得，4其他")
    private Integer rightsAcquiringWay;

    /**
     *   查询人性质:1软著登记人,2司法机关
     */
    @ApiModelProperty(value = "查询人性质:1软著登记人,2司法机关")
    private String queryType;

    /**
     *   查询目的：[1转让2承受3合同4撤销5变更补充6补发7复印原始档案8其他],2司法机关[1司法机关调查取证2司法机关查封或解封3其他]
     */
    @ApiModelProperty(value = "1软件登记人[1转让2承受3合同4撤销5变更补充6补发7复印原始档案8其他],2司法机关[1司法机关调查取证2司法机关查封或解封3其他]")
    private String queryPurpose;

    /**
     *   其他说明
     */
    @ApiModelProperty(value = "其他说明")
    private String otherInfo;

    /**
     *   查询要求：1不调取档案、2调取全部档案、3调取XXX部分档案
     */
    @ApiModelProperty(value = "查询要求：1不调取档案、2调取全部档案、3调取XXX部分档案")
    private Integer queryRequirement;

    /**
     *   query_requirement=2时替换XXX
     */
    @ApiModelProperty(value = "query_requirement=2时替换XXX")
    private String queryRequirementDesc;

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