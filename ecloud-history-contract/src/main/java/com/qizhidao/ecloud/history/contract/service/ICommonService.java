package com.qizhidao.ecloud.history.contract.service;

import com.qizhidao.ecloud.framework.common.http.ResponseVO;
import com.qizhidao.ecloud.history.contract.constant.LookupClassifyEnum;
import com.qizhidao.ecloud.history.contract.entity.LookupItem;
import com.qizhidao.ecloud.history.contract.model.vo.LookupItemVO;
import com.qizhidao.ecloud.history.contract.result.AccountSettingDO;
import com.qizhidao.ecloud.history.contract.result.CityBaseInfoResult;
import com.qizhidao.ecloud.history.contract.result.ServiceConfigureProjectResult;
import com.qizhidao.ecloud.history.contract.result.ServiceConfigureResult;
import com.qizhidao.ecloud.history.contract.vo.lookup.LookupItemVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ICommonService {

    List<LookupItemVO> findLookupItemByClassify(LookupItemVO classifyCode);

    List<LookupItemVO> findLookupItemByClassify(LookupItemVO lookupItemVO);

    Map<Integer, LookupItemVO> findItemMapByClassifyCode(String classifyCode);
    
    Map<String, LookupItemVO> findItemCodeMapByClassifyCode(String classifyCode);

    ResponseVO uploadFile(MultipartFile[] files, Integer businessType, Integer documentType, String sizeW,
                          String sizeH, Long employId);

    /**
     * 获取以业务线id为key的map
     */
   // Map<Long, IpsServiceConfigure> mapServiceConfigureById();


    /**
     * 通过 classifyCode 获得配置的基本信息
     */
    List<ServiceConfigureResult> listLookUpConfigureByClassifyCode(LookupClassifyEnum classifyCode);

    /**
     * 根据员工名称（逗号分隔）获取员工id（逗号分隔）
     */
    String getCustomerNameByCustomerId(Long customerId);

    /**
     * 根据客户编号查找客户名称
     */
    String getCustomerNameByCustomerCode(String customerCode);

    /**
     * 根据员工名称（逗号分隔）获取员工id（逗号分隔）
     */
    String getEmployeeIdStrByEmployeeName(String sysBelongUserED, String title);


    /**
     * 按用户名校验用户是否存在
     * @param employeeNameComma
     * @param title
     * @return  Map<String,String> key=exists 表示存在，value是对应的id，key=nonExists,value对应的用户名，表示不存在
     */
    Map<String,String> getUserIdStrByEmployeeName(String employeeNameComma, String title);

    /**
     * 通过客户名称获取客户编号
     */
    String getCustomerCodeByCustomerName(String customerName);

    /**
     * 查询省市区信息集合
     */
    List<CityBaseInfoResult> listCityBaseInfo();

    /**
     * 根据员工ID获取名称
     *
     * @param id 员工ID
     * @return 员工名称
     */
    String findEmployeeNameById(Long id);

    /**
     * 获取商标分类
     *
     * name = concat(bigtypename, '_', year) as name
     */
    List<ServiceConfigureResult> listTMBigType();
    Map<Long,ServiceConfigureResult> listTMBigTypeMap();

    /**
     * 获取项目阶段 -> 项目进度 -> 项目状态 联动数据
     */
    List<ServiceConfigureProjectResult> listLookUpConfigure4Project();


    /**
     * 获取币种主键&名称
     */
    Map<Long, String> mapByCurrencyId();

    /**
     * 获取币种名称&主键
     */
    Map<String, Long> mapByCurrencyName();

    /**
     * 获取erp财务管理收款信息
     */
    List<AccountSettingDO> listErpBillSetting();

    /**
     * 根据业务类型ID获取对应的名称
     *
     * @param businessId 业务类型ID
     * @return ServiceConfigureResult
     */
    @Deprecated
    ServiceConfigureResult findServiceBranchClassById(Long businessId);

    /**
     * 商标分类小类
     *
     * @return List<ServiceConfigureResult>
     */
    List<ServiceConfigureResult> listTMSmallType(Long classifyVersion);

    /**
     * 获取lookup列表，根据业务分类(过渡模块)
     * @param itemCode
     * @param classifyCode
     * @param itemAttr3
     * @return
     */
    List<LookupItem> selectByBusinessClassifyCode(String itemCode, String classifyCode, String itemAttr3);
    /**
     * 获取value
     * @param itemCode
     * @param classifyCode
     * @param itemAttr3
     * @return
     */
    LookupItem findValueByItemCode(String itemCode, String classifyCode, String itemAttr3);

    /**
     * 获取value
     * @param businessCode
     * @return
     */
    Map<String,Map<String,String>> getStatusCodeTransformName(String businessCode);

    Map<String,Map<String,String>> getStatusNameTransformCode(String businessCode);

    /**
     *
     * @Description: TODO
     * @param: @param businessCode
     * @param: @param businessClassifyCode
     * @param: @param serviceProductCode
     * @param: @return
     * @return: List<LookupItemVO>
     * @time： 2019年6月25日 上午10:08:04
     * @throws
     */
    List<LookupItemVO> getIPSCaseStatusLookupList(String businessCode, String businessClassifyCode, String serviceProductCode);

    LookupItemVO getIPSCaseStatusLookup(String businessCode, String businessClassifyCode, String serviceProductCode, String itemCode);


    Map<String,String> getProcessCodeTransformName(String businessCode);

    /**
     * 获取提交官方前的状态
     * @return
     */
    List<String> getPatentStatus(String businessClassifyCode, String nodeStatusName);

    /**
     * 根据名称获取状态码
     * @param businessClassifyCode
     * @param nodeStatusName
     * @return
     */
    Integer getPatentStatusCode(String businessClassifyCode, String nodeStatusName);

    List<LookupItem> findItemInItemCodeList(String itemCode, String classifyCode);


    /**
     * 按对象查询
     * @param param
     * @return
     */
    List<LookupItemVO> getIPSCaseStatusLookupList(LookupItemVO param);

}
