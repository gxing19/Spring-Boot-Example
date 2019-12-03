package com.qizhidao.ecloud.history.contract.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qizhidao.ecloud.history.contract.entity.base.BaseResource;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Copyright extends BaseResource {
    private static final long serialVersionUID = 3396471278584472145L;
    /**
     *   表主键
     */
    private Long copyrightId;

    /**
     *   ips_contract_t表主键
     */
    private Long contractId;

    private String customerCode;

    private String customerName;

    /**
     * 合同编码
     */
    private String contractCode;

    /**
     *   系统版权编号
     */
    private String sysCopyrightNo;

    /**
     *   线下版权编号
     */
    private String copyrightNo;

    /**
     *   金额
     */
    private BigDecimal caseAmount;

    /**
     *   业务分类ID
     */
    private Integer businessClassifyId;

    /**
     *   产品类型ID
     */
    private Integer productTypeId;

    /**
     *   案件途径ID
     */
    private Integer caseChannelId;

    private String businessClassifyCode;
    private String productTypeCode;
    private String caseChannelCode;

    /**
     *   立案时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate caseDate;
    /**
     * 立案提交时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fillingCommitDate;

    /**
     *   IPS状态(tpl_lookup_item_t表主键ID,classify code为COPYRIGHT_STATUS)
     */
    private Integer status;

    /**
     */
    private String remark;

    /**
     *   版本
     */
    private Integer version;

    /**
     * 案件来源
     */
    private Integer caseSrc;

    @ApiModelProperty(value = "商城订单号")
    private String  orderNo;

    /**
     *   是否加急(Y=加急,N=普通)
     */
    private String urgentFlag;

    /**
     *   加急时间
     */
    private String urgentDate;

    /**
     * 流程key
     */
    private String flowKey;

    private String officeCode;

    private String treeCode;
    /**
     * 服务程序编码=版权案件页面的案件类型
     */
    @ApiModelProperty(value = "服务程序编码")
    private String  processCode;

    /**
     * 服务产品编码
     */
    @ApiModelProperty(value = "服务产品编码")
    private String serviceProductCode;

    @ApiModelProperty(value = "付款方式类型")
    private String pricingType;

    @ApiModelProperty(value = "付款方式")
    private String pricingMode;
    /**
     *   登记申请流水号
     */
    @ApiModelProperty(value = "登记申请流水号")
    private String registerApplyNo;

    @ApiModelProperty(value = "软件全称")
    private String fullName;

    @ApiModelProperty(value = "作品名称")
    private String workName;

    @ApiModelProperty(value = "案件名：软件名或作品名")
    private String caseName;

    @ApiModelProperty(value = "官费")
    private BigDecimal officialFee;

    @ApiModelProperty(value = "加急费")
    private BigDecimal urgentFee;

}