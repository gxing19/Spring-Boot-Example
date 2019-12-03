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
@ApiModel(value = "CopyrightWorkRegistration", description = "作品版权登记-基本信息表")
public class CopyrightWorkRegistration {
    /**
     *   表主键
     */
    @ApiModelProperty(value = "作品登记ID")
    private Long workRegistrationId;

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
     *   作者姓名或名称
     */
    @ApiModelProperty(value = "作者姓名或名称")
    private String author;

    /**
     *   作品署名
     */
    @ApiModelProperty(value = "作品署名")
    private String workSignature;

    /**F
     *   1原创2改编3翻译4汇编5注释6整理7其他
     */
    @ApiModelProperty(value = "1原创2改编3翻译4汇编5注释6整理7其他")
    private Integer workCreationQuality;

    /**
     *   原创说明
     */
    @ApiModelProperty(value = "原创说明")
    private String originalDesc;

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
     *   申请类型(A01=变更登记,A02=补充登记)
     */
    @ApiModelProperty(value = "申请类型(A01=变更登记,A02=补充登记)")
    private String applyType;

    /**
     *   变更理由说明
     */
    @ApiModelProperty(value = "变更理由说明")
    private String changeDescription;

    /**
     *   创作完成日期
     */
    @ApiModelProperty(value = "创作完成日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate creationCompletionDate;

    /**
     *   创作完成地点
     */
    @ApiModelProperty(value = "创作完成地点")
    private String creationCompletionCode;

    /**
     *   创作完成城市
     */
    @ApiModelProperty(value = "创作完成城市")
    private String creationCompletionCity;

    /**
     *   发表状态(1=已发表,2=未发表)
     */
    @ApiModelProperty(value = "发表状态(1=已发表,2=未发表)")
    private Integer publishStatus;

    /**
     *   首次发表日期
     */
    @ApiModelProperty(value = "首次发表日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate publishDate;

    /**
     *   首次发表地点(国家)
     */
    @ApiModelProperty(value = "首次发表地点")
    private String publishPlaceCode;

    /**
     *   首次发表城市
     */
    @ApiModelProperty(value = "首次发表城市")
    private String publishPlaceCity;

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