/**
 * Copyright © 2018, ZhiYun Network Co.,Ltd. All rights reserved. ZhiYun PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package com.qizhidao.ecloud.history.contract.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * ClassName: com.qizhidao.ecloud.history.contract.vo.base.ContractBaseInfoVO
 *
 * Description: 案件详情页面 合同头部信息
 *
 * Date: 11/20/18
 *
 * @author James
 * @version 1.0.0
 */
@Getter
@Setter
@Accessors(chain = true)
public class ContractBaseInfoResult {

    private Long contractId;
    /**
     * 系统合同编号
     */
    private String sysContractNo;

    /**
     * 线下合同编号
     */
    private String contractNo;

    /**
     * 所属办事处
     */
    private String officeCode;

    /**
     * 公司编码
     */
    private String aliasCode;
    
    private String customerCode;
    
    private String customerName;
    
    

}
