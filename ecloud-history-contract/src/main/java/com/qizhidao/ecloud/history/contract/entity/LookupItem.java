package com.qizhidao.ecloud.history.contract.entity;


import com.qizhidao.ecloud.history.contract.entity.base.BaseResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LookupItem extends BaseResource {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer itemId;
	
	private String itemCode;
	
	private String itemName;
	
	private String classifyCode;
	
	private String itemParentCode;
	
	private String language;
	
	private Integer status;
	
	private Integer itemIndex;
	
	private String itemAttr1;
	
	private String itemAttr2;

	private String itemAttr3;

	private String itemAttr4;

	private String itemAttr5;

	private String itemAttr6;

	private String itemAttr7;

	private String itemAttr8;

	private String itemAttr9;

	private String itemAttr10;
	
	private String description;
	
	private String appName;
}
