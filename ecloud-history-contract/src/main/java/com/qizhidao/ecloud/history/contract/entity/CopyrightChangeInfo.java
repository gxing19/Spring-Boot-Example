package com.qizhidao.ecloud.history.contract.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(value = "CopyrightChangeInfo", description = "软著版权变更-基本信息表")
public class CopyrightChangeInfo {
    /**
     * 表主键
     */
    @ApiModelProperty(value = "版权变更ID")
    private Long copyrightChangeId;

    /**
     * ips_cr_copyright_t表主键
     */
    @ApiModelProperty(value = "版权ID")
    private Long copyrightId;

    /**
     * 软件全称
     */
    @ApiModelProperty(value = "软件全称")
    private String fullName;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private String versionNo;

    /**
     * 原登记类别:0著作权登记,1转让合同登记,2许可合同登记
     */
    @ApiModelProperty(value = "原登记类别:0著作权登记,1转让合同登记,2许可合同登记")
    private Integer oldRegisterType;

    /**
     * 原登记号
     */
    @ApiModelProperty(value = "原登记号")
    private String oldRegisterNo;

    /**
     * 原变更/补充登记证明编号
     */
    @ApiModelProperty(value = "原变更/补充登记证明编号")
    private String oldCertifyNo;

    /**
     * 登记申请流水号
     */
    @ApiModelProperty(value = "登记申请流水号")
    private String registerApplyNo;

    /**
     * 申请类型(A01=变更登记,A02=补充登记)
     */
    @ApiModelProperty(value = "申请类型(A01=变更登记,A02=补充登记)")
    private String applyType;

    /**
     * 变更理由说明
     */
    @ApiModelProperty(value = "变更理由说明")
    private String changeDescription;

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
     * 变更信息内容列表
     */
    @ApiModelProperty("变更信息内容列表")
    private List<CopyrightChangeDetail> changeDetailList;

    /**
     * 著作权人
     */
    @ApiModelProperty("著作权人")
    private String copyrightOwner;
}