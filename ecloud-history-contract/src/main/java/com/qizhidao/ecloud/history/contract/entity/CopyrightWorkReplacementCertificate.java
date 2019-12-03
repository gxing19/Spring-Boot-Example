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
@ApiModel(value = "CopyrightWorkReplacementCertificate", description = "作品版权补换证书-基本信息表")
public class CopyrightWorkReplacementCertificate {
    /**
     *   表主键
     */
    @ApiModelProperty(value = "作品补换证书ID")
    private Long workReplacementId;

    /**
     *   ips_cr_copyright_t表主键
     */
    @ApiModelProperty(value = "版权ID")
    private Long copyrightId;

    /**
     *   作品名称
     */
    @ApiModelProperty(value = "作品名称")
    private String workName;

    /**
     *   原登记类型lookup:01作品著作权登记02著作权合同备案03与著作权有关权利事项登记04重印国外期刊合同登记05著作权质权登记
     */
    @ApiModelProperty(value = "原登记类型lookup:01作品著作权登记02著作权合同备案03与著作权有关权利事项登记04重印国外期刊合同登记05著作权质权登记")
    private String oldRegisterType;

    /**
     *   原登记号
     */
    @ApiModelProperty(value = "原登记号")
    private String oldRegisterNo;

    /**
     *   原登记日期
     */
    @ApiModelProperty(value = "原登记日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate oldRegisterDate;

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

    /**
     * 著作权人
     */
    private String copyrightOwner;

    /**
     * 作者
     */
    private String author;
}