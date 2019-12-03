/**
 * Copyright © 2018, ZhiYun Network Co.,Ltd. All rights reserved. ZhiYun PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package com.qizhidao.ecloud.history.contract.model.doo;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ClassName: com.qizhidao.ecloud.common.business.ServiceCaseModeDO
 * <p>
 * Description:
 * <p>
 * Date: 2019/2/3/003 - 23:20
 *
 * @author shun.li
 * @version 1.0.0
 */
@ApiModel(value = "ServiceCaseModeDO", description = "案件途径对象")
public class ServiceCaseModeDO {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "业务线code")
    private String businessCode;

    @ApiModelProperty(value = "案件途径代码")
    private String businessCaseCode;

    @ApiModelProperty(value = "案件途径名称")
    private String businessCaseName;

    @ApiModelProperty(value = "案件途径说明")
    private String businessCaseContent;

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessCaseCode() {
        return businessCaseCode;
    }

    public void setBusinessCaseCode(String businessCaseCode) {
        this.businessCaseCode = businessCaseCode;
    }

    public String getBusinessCaseName() {
        return businessCaseName;
    }

    public void setBusinessCaseName(String businessCaseName) {
        this.businessCaseName = businessCaseName;
    }

    public String getBusinessCaseContent() {
        return businessCaseContent;
    }

    public void setBusinessCaseContent(String businessCaseContent) {
        this.businessCaseContent = businessCaseContent;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
