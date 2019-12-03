package com.qizhidao.ecloud.history.contract.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(value = "CopyrightReplacementCertificate", description = "软著版权补/换证书基本信息表")
public class CopyrightReplacementCertificate {
    /**
     *   表主键
     */
    @ApiModelProperty(value = "表主键")
    private Long copyrightReplacementId;

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
     *   补换证书类型:1著作权登记证书，2软件合同登记证书，3变更补充证明
     */
    @ApiModelProperty(value = "补换证书类型:1著作权登记证书，2软件合同登记证书，3变更补充证明")
    private Integer replacementCertificateType;

    /**
     *   补发或者换发的理由
     */
    @ApiModelProperty(value = "补发或者换发的理由")
    private String replacementCertificateReason;

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