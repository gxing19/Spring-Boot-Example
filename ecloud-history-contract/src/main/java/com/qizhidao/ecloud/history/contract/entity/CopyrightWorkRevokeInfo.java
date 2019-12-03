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
@ApiModel(value = "CopyrightWorkRevokeInfo", description = "作品版权撤消-基本信息表")
public class CopyrightWorkRevokeInfo {
    /**
     *   表主键
     */
    @ApiModelProperty(value = "撤消ID")
    private Long workRevokeId;

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
     *   登记申请流水号
     */
    @ApiModelProperty(value = "登记申请流水号")
    private String registerApplyNo;

    /**
     *   原登记日期
     */
    @ApiModelProperty(value = "原登记日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate oldRegisterDate;

    /**
     *   原登记号
     */
    @ApiModelProperty(value = "原登记号")
    private String oldRegisterNo;

    /**
     *   原登记类别:0作品著作权登记,1著作权合同备案,2与著作权有关权利事项登记，3重印国外期刊合同登记
     */
    @ApiModelProperty(value = "原登记类别:0作品著作权登记,1著作权合同备案,2与著作权有关权利事项登记，3重印国外期刊合同登记")
    private Integer oldRegisterType;

    /**
     *   撤消依据:1申请人申请撤销、2第三方申请撤销、3其他
     */
    @ApiModelProperty(value = "撤消依据:1申请人申请撤销、2第三方申请撤销、3其他")
    private String revokeBasis;

    /**
     *   撤消理由
     */
    @ApiModelProperty(value = "撤消理由")
    private String revokeReason;

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