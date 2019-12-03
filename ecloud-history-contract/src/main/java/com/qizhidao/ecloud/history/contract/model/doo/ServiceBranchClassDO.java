/**
 * Copyright © 2018, ZhiYun Network Co.,Ltd. All rights reserved. ZhiYun PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package com.qizhidao.ecloud.history.contract.model.doo;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ClassName: com.qizhidao.ecloud.common.business.ServiceBranchClassDO
 * <p>
 * Description:
 * <p>
 * Date: 2019/2/5/005 - 12:36
 *
 * @author shun.li
 * @version 1.0.0
 */
@ApiModel(value = "ServiceBranchClassDO", description = "业务分类对象")
public class ServiceBranchClassDO {

    private Long id;

    @ApiModelProperty(value = "业务线code")
    private String businessCode;

    @ApiModelProperty(value = "业务分类代码")
    private String businessBranchCode;

    @ApiModelProperty(value = "业务分类名称")
    private String businessBranchName;

    @ApiModelProperty(value = "产品说明")
    private String businessBranchContent;


    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessBranchCode() {
        return businessBranchCode;
    }

    public void setBusinessBranchCode(String businessBranchCode) {
        this.businessBranchCode = businessBranchCode;
    }

    public String getBusinessBranchName() {
        return businessBranchName;
    }

    public void setBusinessBranchName(String businessBranchName) {
        this.businessBranchName = businessBranchName;
    }

    public String getBusinessBranchContent() {
        return businessBranchContent;
    }

    public void setBusinessBranchContent(String businessBranchContent) {
        this.businessBranchContent = businessBranchContent;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
