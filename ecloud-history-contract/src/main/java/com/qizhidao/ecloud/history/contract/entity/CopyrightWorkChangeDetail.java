package com.qizhidao.ecloud.history.contract.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(value = "CopyrightWorkChangeDetail", description = "作品版权变更-详情表")
public class CopyrightWorkChangeDetail {
    /**
     *   表主键
     */
    @ApiModelProperty(value = "作品变更详情主键")
    private Long id;

    /**
     *   ips_cr_copyright_change_info_t表主键
     */
    @ApiModelProperty(value = "作品版权变更ID")
    private Long workChangeId;

    /**
     *   ips_cr_copyright_t表主键
     */
    @ApiModelProperty(value = "版权ID")
    private Long copyrightId;

    /**
     *   申请类型(B01=变更登记,B02=补充登记)
     */
    @ApiModelProperty(value = "申请类型(B01=变更登记,B02=补充登记)")
    private String applyType;

    /**
     *   变更项或补充项code
     */
    @ApiModelProperty(value = "变更项或补充项code")
    private String changeOptionCode;

    /**
     *   变更前内容
     */
    @ApiModelProperty(value = "变更前内容")
    private String changeBeforeContent;

    /**
     *   变更后内容
     */
    @ApiModelProperty(value = "变更后内容")
    private String changeAfterContent;

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