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
@ApiModel(value = "CopyrightWorkTransferInfo", description = "作品版权转让-基本信息表")
public class CopyrightWorkTransferInfo {
    /**
     *   表主键
     */
    @ApiModelProperty(value = "作品转让ID")
    private Long workTransferId;

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
     *   作品数量
     */
    @ApiModelProperty(value = "作品数量")
    private String workCount;

    /**
     *   登记号
     */
    @ApiModelProperty(value = "登记号")
    private String oldRegisterNo;

    /**
     *   登记申请流水号
     */
    @ApiModelProperty(value = "登记申请流水号")
    private String registerApplyNo;

    /**
     *   合同类型:1转让合同，2专有许可合同，3非专有许可合同
     */
    @ApiModelProperty(value = "合同类型:1转让合同，2专有许可合同，3非专有许可合同")
    private Integer contractType;

    /**
     *   权利范围lookup:01发表权,02署名权,03修改权,04保护作品完整权,05复制权,06发行权,07出租权,08展览权,09表演权,10放映权,11广播权,12信息网络传播权,13摄制权,14改编权,15翻译权,16汇编权,17其他
     */
    @ApiModelProperty(value = "权利范围lookup配置")
    private String transferRightScope;

    @ApiModelProperty(value = "其他")
    private String otherInfo;

    /**
     *   地域限国家，限国家表
     */
    @ApiModelProperty(value = "地域限国家，限国家表")
    private String regionCountryScope;

    /**
     *   地域限城市
     */
    @ApiModelProperty(value = "地域限城市")
    private String regionCityScope;

    /**
     *   是否永久:N否，Y是
     */
    @ApiModelProperty(value = "是否永久:N否，Y是")
    private String permanentFlag;

    /**
     *   合同生效日期开始时间
     */
    @ApiModelProperty(value = "合同生效日期开始时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startEffectiveDate;

    /**
     *   合同生效日期结束时间
     */
    @ApiModelProperty(value = "合同生效日期结束时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endEffectiveDate;

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


    @ApiModelProperty("作品版权转让详情列表(列表)")
    private List<CopyrightWorkTransferDetail> workTransferDetails;
}