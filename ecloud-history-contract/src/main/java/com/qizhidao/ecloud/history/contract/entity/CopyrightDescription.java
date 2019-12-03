package com.qizhidao.ecloud.history.contract.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CopyrightDescription {
    /**
     *   表主键
     */
    private Long rightDescId;

    /**
     *   ips_cr_copyright_t表主键
     */
    private Long copyrightId;


    /**
     *   软件是否已登记(Y=选中,N=未选中)
     */
    @ApiModelProperty(value = "软件是否已登记(Y=选中,N=未选中)")
    private String softwareRegisterFlag;
    
    /**
     *   权利取得方式(1=原始取得,2=继受取得)
     */
    @ApiModelProperty(value = "权利取得方式(1=原始取得,2=继受取得)")
    private Integer obtainMode;

    /**
     *   取得方式(1=受让,2=承受,3=继承)
     */
    @ApiModelProperty(value = "取得方式(1=受让,2=承受,3=继承)")
    private Integer obtainModeType;
    /**
     *   范围(发表权,署名权,修改权,复制权,发行权,出租权,信息网络传播权,翻译权,应当优著作权人享有其他权利,其它)
     *   权利范围lookup:01发表权,02署名权,03修改权,04保护作品完整权,05复制权,06发行权,07出租权,08展览权,09表演权,10放映权,11广播权,12信息网络传播权,13摄制权,14改编权,15翻译权,16汇编权,17其他
     */
    @ApiModelProperty(value = "权利范围 编码")
    private String scope;
    
    /**
     * 权利范围说明
     */
    @ApiModelProperty(value = "权利范围说明")
    private String scopeDesc;
    
    /**
     *   原登记号
     */
    @ApiModelProperty(value = "原登记号")
    private String registerNo;

    /**
     *   原登记是否做过变更或补充(Y=选中,N=未选中)
     */
    @ApiModelProperty(value = " 原登记是否做过变更或补充(Y=选中,N=未选中)")
    private String registerChangeFlag;

    /**
     *   变更或补充证明编号
     */
    @ApiModelProperty(value = " 变更或补充证明编号")
    private String changeNo;


    /**
     *   创建人
     */
    private Long createBy;

    /**
     *   创建时间
     */
    private Date createDate;

    /**
     *   最后更新人
     */
    private Long lastUpdateBy;

    /**
     *   最后更新时间
     */
    private Date lastUpdateDate;

    /**
     *   说明
     */
    private String description;
    
    
    /**
	 * 权利取得方式:0-原始取得，1-继受取得,2-承受,9-其他
	 */
    private Integer getWay;
    /**权利取得方式:0-原始取得，1-继受取得,2-承受,9-其他*/
    private String getWayName;
    /**
     * '权利归属方式,0:个人作品,1:合作作品,2:法人作品,3:职务作品,4:委托作品',
     */
    @ApiModelProperty(value = "'权利归属方式,0:个人作品,1:合作作品,2:法人作品,3:职务作品,4:委托作品'")
    private Integer rightBelongWay;
	/**权利归属方式,0:个人作品,1:合作作品,2:法人作品,3:职务作品,4:委托作品*/
	private String rightBelongWayName;
    /**
     *   权利归属方式说明
     */
    private String rightBelongWayDesc;
    
    @ApiModelProperty(value = "权利拥有状况:0部分,1:全部", required = true, dataType = "Integer")
	private Integer rightRange;
    /**权利拥有状况:0部分,1:全部*/
	private String rightRangeName;
	/**权利拥有状况,
	 * 1:发表权,2:署名权,3:修改权,
	 * 4:保护作品完整权,5:复制权,6:发行权,
	 * 7:出租权,8:展览权,9:表演权,10:放映权,
	 * 11:广播权,12:信息网络传播权,13:摄制权,
	 * 14:改编权,15:翻译权,16:汇编权,99:其他
	 */
	@ApiModelProperty(value = "权利拥有状况,1:发表权,2:署名权,3:修改权,4:保护作品完整权,5:复制权,6:发行权,7:出租权,8:展览权,9:表演权,10:放映权,11:广播权,12:信息网络传播权,13:摄制权,14:改编权,15:翻译权,16:汇编权,99:其他", required = true, dataType = "String")
	private String rightOwned;
	private String rightOwnedName;
	@ApiModelProperty(value = "权利拥有说明", required = false, dataType = "String")
	private String rightOwnedExpain;
	
	@ApiModelProperty(value = "作品样本介质：纸质、电子", required = false, dataType = "String")
	private String productionSample;
	
	@ApiModelProperty(value = "作品样本格式", required = false, dataType = "String")
	private String productionExplain;
	
	@ApiModelProperty(value = "作品样本数量", required = false, dataType = "Integer")
	private Integer productionSampleCount;
}