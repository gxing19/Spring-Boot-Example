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
@ApiModel(value = "CopyrightRevokeInfo", description = "软著版权撤消基本信息表")
public class CopyrightRevokeInfo {
    /**
     *   表主键
     */
    @ApiModelProperty(value = "表主键")
    private Long copyrightRevokeId;

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
     *   原变更/补充登记证明编号
     */
    @ApiModelProperty(value = "原变更/补充登记证明编号")
    private String oldCertifyNo;

    /**
     *   登记申请流水号
     */
    @ApiModelProperty(value = "登记申请流水号")
    private String registerApplyNo;

    /**
     *   原登记类别:0著作权登记,1转让合同登记,2许可合同登记
     */
    @ApiModelProperty(value = "原登记类别:0著作权登记,1转让合同登记,2许可合同登记")
    private Integer registerType;

    /**
     *   撤消理由
     */
    @ApiModelProperty(value = "撤消理由")
    private String revokeReason;

    @ApiModelProperty(value = "有无共同登记人：Y有，N无")
    private String commonRegisterFlag;

    @ApiModelProperty(value = "共同登记人")
    private String commonRegisterPerson;

    @ApiModelProperty(value = "共同登记人意见")
    private String commonRegisterSuggest;

    @ApiModelProperty(value = "登记日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate registerDate;


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