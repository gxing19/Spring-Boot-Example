package com.qizhidao.ecloud.history.contract.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@Accessors(chain = true)
@ApiModel(value = "CopyrightFollowUpInfo", description = "案进跟进信息")
public class CopyrightFollowUpInfo implements Serializable {

    private static final long serialVersionUID = 5875229583671090051L;

    @ApiModelProperty(value = "版权跟进ID，主键")
    private Long copyrightFollowUpId;

    @ApiModelProperty(value = "版权ID")
    private Long copyrightId;

    @ApiModelProperty(value = "接案人")
    private String acceptor;

    @ApiModelProperty(value = "处理人")
    private String handler;

    @ApiModelProperty(value = "质检人")
    private String qualityInspector;

    @ApiModelProperty(value = "提交人")
    private String committer;

    @ApiModelProperty(value = "登记申请流水号")
    private String registerApplyNo;

    @ApiModelProperty(value = "质检时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate qualityInspectionDate;

    @ApiModelProperty(value = "网络登记时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate networkRegisterDate;

    @ApiModelProperty(value = "案件提交时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate caseCommitDate;

    @ApiModelProperty(value = "预计下证时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate preIssuanceDate;

    @ApiModelProperty(value = "实际下证时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate actualIssuanceDate;

    @ApiModelProperty(value = "核准时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate approvalDate;

    @ApiModelProperty(value = "结果公示时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate resultPublishDate;

    @ApiModelProperty(value = "出具报告时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate genReportDate;

    @ApiModelProperty(value = "查询完结时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate queryCompletedDate;

    @ApiModelProperty(value = "撤销核准时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate revokeCheckDate;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新人
     */
    private Long lastUpdateBy;

    /**
     * 最后更新时间
     */
    private Date lastUpdateDate;


}
