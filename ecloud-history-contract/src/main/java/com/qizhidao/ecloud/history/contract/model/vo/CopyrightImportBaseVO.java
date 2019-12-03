package com.qizhidao.ecloud.history.contract.model.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 版权EXCEL导入基础VO
 */
@Data
@Accessors(chain = true)
public class CopyrightImportBaseVO {

    private String errorMsg;
    private Boolean verifyStatus = false;

    //=========================以下案件信息==================================
    /**
     * 线下合同编号
     */
    @Excel(name = "contractNo")
    private String contractNo;
    private Long contractId;
    private String contractCode;
    private String customerName;
    private String customerCode;
    private Long copyrightId;
    /**
     * 是否新数据
     */
    private Boolean whetherNew = false;

    /**
     * 办事处编码
     */
    private String officeCode;

    /**
     * 线下案件编号
     */
    private String copyrightNo;

    /**
     * 案件来源(默认为0历史数据, 1合同立案, 2商城订单)
     */
    private int caseSrc;

    /**
     * 业务分类名称
     */
    private String businessClassifyName;
    private Integer businessClassifyId;

    /**
     * 产品分类名称
     */
    private String productTypeName;
    private Integer productTypeId;

    /**
     * 案件途径
     */
    private String caseChannelName;
    private Integer caseChannelId;

    /**
     * 服务产品编码
     */
    private String serviceProductCode;

    /**
     * 是否加急
     */
    private String urgentFlag;

    /**
     * 加急时间
     */
    private String urgentDate;

    /**
     * 案件金额
     */
    private BigDecimal caseAmount;

    /**
     * 立案时间
     */
    private Date caseDate;

    /**
     * 服务程序编码=版权案件页面的案件类型
     */
    private String processCode;

    /**
     * 案件(ips)状态
     */
    private String ipsStatusName;
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    //==============================以下成本信息===============================

    /**
     * 官费
     */
    private BigDecimal officialFee;

    /**
     * 编写费
     */
    private String writerFee;

    /**
     * 加急费
     */
    private BigDecimal urgentFee;

    //==============================以下案件跟进信息============================

    /**
     * 登记号
     */
    private String registerNo;

    /**
     * 接案人
     */
    private String acceptor;

    /**
     * 处理人
     */
    private String handler;

    /**
     * 质检人
     */
    private String qualityInspector;

    /**
     * 提交人
     */
    private String committer;

    /**
     * 登记申请流水号
     */
    private String registerApplyNo;

    /**
     * 网络登记时间
     */
    private Date networkRegisterDate;

    /**
     * 案件提交时间
     */
    private Date caseCommitDate;

    /**
     * 预计下证时间
     */
    private Date preIssuanceDate;

    /**
     * 实际下证时间
     */
    private Date actualIssuanceDate;

    /**
     * 核准时间
     */
    private Date approvalDate;

    /**
     * 查询完结时间
     */
    private Date queryCompletedDate;

    /**
     * 结果公示时间
     */
    private Date resultPublishDate;

    /**
     * 出具报告时间
     */
    private Date genReportDate;

    //==============================以下申请人信息============================
    /**
     * 姓名或者名称
     */
    private String applicantUser;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 电话号码
     */
    private String telephoneNo;

    /**
     * E-Mail
     */
    private String email;

    /**
     * 手机号码
     */
    private String phoneNo;

    /**
     * 传真号码
     */
    private String faxNo;

    //==============================以下代理人信息============================
    /**
     * 姓名或者名称
     */
    private String agentUserName;
    private Integer agentUserId;

    /**
     * 电话号码
     */
    private String agentTelephoneNo;

    /**
     * 详细地址
     */
    private String agentAddress;

    /**
     * E-Mail
     */
    private String agentEmail;

    /**
     * 邮政编码
     */
    private String agentPostalCode;

    /**
     * 手机号码
     */
    private String agentPhoneNo;

    /**
     * 联系人
     */
    private String agentContactPerson;

    /**
     * 传真号码
     */
    private String agentFaxNo;


}
