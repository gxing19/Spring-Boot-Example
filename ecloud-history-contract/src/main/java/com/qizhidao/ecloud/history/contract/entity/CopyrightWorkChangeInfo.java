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
@ApiModel(value = "CopyrightWorkChangeInfo", description = "作品版权变更-基本信息表")
public class CopyrightWorkChangeInfo {
    /**
     *   表主键
     */
    @ApiModelProperty(value = "作品变更ID")
    private Long workChangeId;

    /**
     *   ips_cr_copyright_t表主键
     */
    @ApiModelProperty(value = "版权ID")
    private Long copyrightId;

    /**
     *   软件全称
     */
    @ApiModelProperty(value = "软件全称")
    private String workName;

    /**
     *   原登记日期
     */
    @ApiModelProperty(value = "原登记日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate oldRegisterDate;

    /**
     *   原登记类别:0作品著作权登记,1著作合同备案,2与著作权有关权利事项登记，3重印国外期刊合同登记
     */
    @ApiModelProperty(value = "原登记类别:0作品著作权登记,1著作合同备案,2与著作权有关权利事项登记，3重印国外期刊合同登记")
    private Integer oldRegisterType;

    /**
     *   原登记号
     */
    @ApiModelProperty(value = "原登记号")
    private String oldRegisterNo;

    /**
     *   原受理号
     */
    @ApiModelProperty(value = "原受理号")
    private String referenceNo;

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

    @ApiModelProperty(value = "变更明细(列表)")
    private List<CopyrightWorkChangeDetail> workChangeDetails;
}