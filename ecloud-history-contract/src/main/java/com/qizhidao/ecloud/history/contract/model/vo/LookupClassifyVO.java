package com.qizhidao.ecloud.history.contract.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * lookup分类属性VO
 */
@Data
@Accessors(chain = true)
public class LookupClassifyVO {
	private Integer classifyId;
    
    private String classifyCode;
    
    private String classifyName;
    
    private Integer status;
    
    private String classifyDesc;
    
    private String description;
    
    private String appName;
}
