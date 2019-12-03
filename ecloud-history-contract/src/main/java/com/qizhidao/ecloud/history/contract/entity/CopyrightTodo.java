package com.qizhidao.ecloud.history.contract.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class CopyrightTodo {
    /**
     *   主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     *   客户编码
     */
    @ApiModelProperty(value = "客户编码")
    private String customerCode;

    /**
     *   客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String customerName;

    /**
     *   合同编码
     */
    @ApiModelProperty(value = "合同编码")
    private String contractCode;

    /**
     *   案件编号
     */
    @ApiModelProperty(value = "案件编号")
    private String sysCaseNo;

    /**
     *   业务主键id
     */
    @ApiModelProperty(value = "业务主键id")
    private Long businessId;

    /**
     *   业务名称
     */
    @ApiModelProperty(value = "业务名称")
    private String businessName;

    @ApiModelProperty(value = "主要负责人标识:N否,Y是")
    private String majorFlag;

    /**
     *   案件金额
     */
    @ApiModelProperty(value = "案件金额")
    private BigDecimal caseAmount;

    /**
     *   服务产品编码
     */
    @ApiModelProperty(value = "服务产品编码")
    private String serviceProductCode;

    /**
     *   业务线分类CODE
     */
    @ApiModelProperty(value = "业务线分类CODE")
    private String businessClassifyCode;
    private String businessClassifyName;

    /**
     *   产品类型CODE
     */
    @ApiModelProperty(value = "产品类型CODE")
    private String productTypeCode;
    private String productTypeName;

    /**
     *   案件途径CODE
     */
    @ApiModelProperty(value = "案件途径CODE")
    private String caseChannelCode;
    private String caseChannelName;

    /**
     *   默认为0(知产运营-分案分案)，1(知产运营-案件实施)
     */
    @ApiModelProperty(value = "默认为0(知产运营-分案分案)，1(知产运营-案件实施)")
    private Integer flowType;

    /**
     *   默认为0待办，1已办
     */
    @ApiModelProperty(value = "默认为0待办，1已办")
    private Integer status;

    /**
     *   办事处(带数字)
     */
    @ApiModelProperty(value = "办事处(带数字)")
    private String officeCode;
    private String officeName;

    /**
     *   办事处(带字母)
     */
    @ApiModelProperty(value = "办事处(带字母)")
    private String treeCode;

    /**
     *   谈单人id多个
     */
    @ApiModelProperty(value = "谈单人id多个")
    private String catcherIds;

    /**
     *   谈单人多个名字
     */
    @ApiModelProperty(value = "谈单人多个名字")
    private String catcherUsers;

    /**
     *   立案时间
     */
    @ApiModelProperty(value = "立案时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate filingDate;
    private String filingDateStart;
    private String filingDateEnd;

    /**
     *   分案人
     */
    @ApiModelProperty(value = "分案人")
    private Long assignPersonId;
    @ApiModelProperty(value = "搜索关键字")
    private String searchKey;

    /**
     *   接案人
     */
    @ApiModelProperty(value = "接案人")
    private Long receivePersonId;

    /**
     * 案件来源(默认为0历史数据，1合同立案 2商城订单)
     */
    @ApiModelProperty(value = "案件来源(默认为0历史数据，1合同立案 2商城订单)")
    private Integer caseSrc;

    /**
     * 商城订单号
     */
    @ApiModelProperty(value = "商城订单号")
    private String orderNo;

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
     *   备注
     */
    private String description;
}