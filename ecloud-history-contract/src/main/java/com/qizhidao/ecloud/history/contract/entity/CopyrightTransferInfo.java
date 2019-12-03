package com.qizhidao.ecloud.history.contract.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(value = "CopyrightTransferInfo", description = "软著版权转让许可-基本信息表")
public class CopyrightTransferInfo {
    /**
     *   表主键
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
     *   合同类型:1转让合同，2专有许可合同
     */
    @ApiModelProperty(value = "合同类型:1转让合同，2专有许可合同")
    private Integer contractType;

    /**
     *   转让权利范围:1全部权利，2部分权利
     */
    @ApiModelProperty(value = "转让权利范围:1全部权利，2部分权利")
    private Integer transferRightScope;

    /**
     *   转让地域范围:1无地域范围限制，2中国地域包含港澳台，3不含港澳台，4其他地域
     */
    @ApiModelProperty(value = " 转让地域范围:1无地域范围限制，2中国地域包含港澳台，3不含港澳台，4其他地域")
    private Integer transferRegionScope;

    /**
     *   转让合同生效日期
     */
    @ApiModelProperty(value = "转让合同生效日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate contractEffectiveDate;

    /**
     *   许可权利期限  开始
     */
    @ApiModelProperty(value = "许可权利期限  开始")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startPermissionDate;
    /**
     *   许可权利期限  结束
     */
    @ApiModelProperty(value = "许可权利期限  结束")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endPermissionDate;

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
     * 转让信息
     */
    @ApiModelProperty("软著版权转让许可详情信息(列表)")
    private List<CopyrightTransferDetail> copyrightTransferDetails;

    /**
     * 权利取得方式 1-受让,2-许可
     */
    private Integer gainRightWay;
}