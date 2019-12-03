/**
 * Copyright © 2018, ZhiYun Network Co.,Ltd. All rights reserved. ZhiYun PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package com.qizhidao.ecloud.history.contract.service;

import com.alibaba.fastjson.JSONObject;
import com.qizhidao.ecloud.history.contract.model.doo.*;
import com.qizhidao.ecloud.history.contract.result.CityBaseInfoResult;
import com.qizhidao.ecloud.history.contract.result.CurrencyInfoResult;
import com.qizhidao.ecloud.history.contract.result.ServiceConfigureResult;
import com.qizhidao.ecloud.history.contract.vo.message.SyncCaseStatusInfo;
import com.qizhidao.ecloud.history.contract.vo.remote.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName: com.qizhidao.ecloud.history.contract.service.ICommonRemoteService
 * <p>
 * Description:
 * <p>
 * Date: 2019/4/26/026 - 23:53
 *
 * @author shun.li
 * @version 1.0.0
 */
public interface ICommonRemoteService {

    /**
     * @param businessCode
     * @return
     * @Description 获取指定业务线下面的所有业务分类信息.key:ID,value:Name
     * @author tanle
     * @since 2019年5月27日
     */
    Map<Long, String> getBranchIdTransformName(String businessCode);

    /**
     * @param businessCode
     * @return
     * @Description 获取指定业务线下面的所有业务分类信息.key:Name,value:ID
     * @author tanle
     * @since 2019年5月27日
     */
    Map<String, Long> getBranchNameTransformId(String businessCode);

    /**
     * @param businessCode
     * @return
     * @Description 获取指定业务线下面的所有业务分类信息.key:ID,value:Code
     * @author tanle
     * @since 2019年5月27日
     */
    Map<Long, String> getBranchIdTransformCode(String businessCode);

    Map<String, Long> getBranchCodeTransformId(String businessCode);

    /**
     * CODE -> name
     *
     * @param businessCode
     * @return
     */
    Map<String, String> getBranchCodeTransformName(String businessCode);

    /**
     * @param businessCode
     * @return
     * @Description 获取指定业务线下面的所有业务分类信息.key:name,value:VO对象
     * @author tanle
     * @since 2019年5月27日
     */
    public Map<String, ServiceConfigureResult> getBranchNameTransformVO(String businessCode);

    /**
     * @param businessCode
     * @return
     * @Description 获取指定业务线下面的所有业务分类信息
     * @author tanle
     * @since 2019年5月27日
     */
    List<ServiceBranchClassDO> getAllBusinessBranchInfo(String businessCode);

    /**
     * @param businessCode
     * @return
     * @Description 获取指定业务线下面的所有产品分类信息.key:ID,value:Name
     * @author tanle
     * @since 2019年5月27日
     */
    Map<Long, String> getProjectClassIdTransformName(String businessCode);

    /**
     * CODE ->NAME
     *
     * @param businessCode
     * @return
     */
    Map<String, String> getProjectCodeTransformName(String businessCode);

    Map<String, Long> getProjectCodeTransformId(String businessCode);

    /**
     * 获取指定业务线下面的所有产品分类信息
     *
     * @throws
     * @Description: TODO
     * @param: @param businessCode
     * @param: @return
     * @return: List<ServiceProjectClassDO>
     * @time： 2019年6月10日 上午11:15:08
     */
    List<ServiceProjectClassDO> getAllProjectClassInfo(String businessCode);

    /**
     * 获取指定业务线下面的所有案件途径信息
     *
     * @throws
     * @Description: TODO
     * @param: @param businessCode
     * @param: @return
     * @return: List<ServiceProjectClassDO>
     * @time： 2019年6月10日 上午11:15:08
     */
    List<ServiceCaseModeDO> getAllCaseModeInfo(String businessCode);

    /**
     * @param businessCode
     * @return
     * @Description 获取指定业务线下面的所有产品分类信息.key:Name,value:ID
     * @author tanle
     * @since 2019年5月27日
     */
    public Map<String, Long> getProjectClassNameTransformId(String businessCode);

    /**
     * @param businessCode
     * @return
     * @Description 获取指定业务线下面的所有产品分类信息.key:ID,value:Code
     * @author tanle
     * @since 2019年5月27日
     */
    public Map<Long, String> getProjectClassIdTransformCode(String businessCode);

    /**
     * @param businessCode
     * @return
     * @Description 获取指定业务线下面的所有产品分类信息.key:name,value:VO对象
     * @author tanle
     * @since 2019年5月27日
     */
    public Map<String, ServiceConfigureResult> getProjectClassNameTransformVO(String businessCode);

    /**
     * @param businessCode
     * @return
     * @Description 获取指定业务线下面的所有案件途径信息.key:ID,value:Name
     * @author tanle
     * @since 2019年5月27日
     */
    public Map<Long, String> getCaseModeIdTransformName(String businessCode);

    public Map<String, String> getCaseCodeTransformName(String businessCode);

    public Map<String, Long> getCaseCodeTransformId(String businessCode);

    /**
     * @param businessCode
     * @return
     * @Description 获取指定业务线下面的所有案件途径信息.key:Name,value:ID
     * @author tanle
     * @since 2019年5月27日
     */
    public Map<String, Long> getCaseModeNameTransformId(String businessCode);

    /**
     * @param businessCode
     * @return
     * @Description 获取指定业务线下面的所有案件途径信息.key:ID,value:Code
     * @author tanle
     * @since 2019年5月27日
     */
    public Map<Long, String> getCaseModeIdTransformCode(String businessCode);

    public Map<String, Long> getCaseModeCodeTransformId(String businessCode);

    /**
     * @param businessCode
     * @return
     * @Description 获取指定业务线下面的所有案件途径信息.key:name,value:VO对象
     * @author tanle
     * @since 2019年5月27日
     */
    public Map<String, ServiceConfigureResult> getCaseModeNameTransformVO(String businessCode);

    /**
     * @return
     * @Description 获取所有的业务线信息.key:ID,value:Name
     * @author tanle
     * @since 2019年5月27日
     */
    public Map<Long, String> getServiceConfigIdTransformName();

    /**
     * @return
     * @Description 获取所有的业务线信息.key:ID,value:Code
     * @author tanle
     * @since 2019年5月27日
     */
    public Map<Long, String> getServiceConfigIdTransformCode();

    /**
     * @return
     * @Description 获取所有的业务线信息.key:Code,value:Id
     * @author tanle
     * @since 2019年5月27日
     */
    public Map<String, Long> getServiceConfigCodeTransformId();


    /**
     * 通过客户编码获取客户名称
     *
     * @param Code
     * @return
     */
    CustomerDO getCustomerInfo(String Code);


    /**
     * 批量获取客户名称
     *
     * @param codeList
     * @return
     */
    List<CustomerDO> getCustomerInfo(List<String> codeList);

    /**
     * @param customerName
     * @return
     * @Description 通过名称模糊查询出对应的客户编码
     * @author tanle
     * @since 2019年5月6日
     */
    List<String> getCustomerCodesByName(String customerName);

    /**
     * 根据客户公司名称模糊查询客户列表
     *
     * @param customerName
     * @return
     */
    List<String> getCustomerListInfoByName(String customerName);

    /**
     * 查询省市区名称
     *
     * @param provinceCode
     * @param cityCode
     * @param countyCode
     * @return
     */
    JSONObject getRegionInfo(Long provinceCode, Long cityCode, Long countyCode);

    /**
     * 根据城市ids查询出城市名
     *
     * @throws
     * @Description: TODO
     * @param: @param ids
     * @param: @return
     * @return: Map<Integer       ,       String>
     * @time： 2019年7月26日 下午9:43:23
     */
    Map<Integer, String> getCityCode4NameMap(String ids);

    /**
     * 获取国家名称
     *
     * @param id
     * @return
     */
    CountryDO findCountryById(Long id);

    /**
     * @param currencyId
     * @return
     * @Description 通过ID获取币种信息
     * @author tanle
     * @since 2019年4月30日
     */
    CurrencyInfoResult getCurrencyInfo(Long currencyId);

    /**
     * @param currencyName
     * @return
     */
    CurrencyInfoResult getCurrencyInfo(String currencyName);

    /**
     * @return
     * @Description 获取所有的币种信息
     * @author tanle
     * @since 2019年5月6日
     */
    List<CurrencyInfoResult> getAllCurrency();

    /**
     * 获取所有的币种信息
     *
     * @return
     */
    Map<String, String> mapByCurrencyName();

    /**
     * @return
     * @Description 获取省市区三级结构的数据
     * @author tanle
     * @since 2019年4月30日
     */
    List<CityBaseInfoResult> getThreeLevelCityInfo();

    /**
     * 根据行政级别查询:"级别:0,中国；1，省分；2，市；3，区、县"
     *
     * @param level
     * @return
     */
    List<CityDO> findCityByLevel(int level);


    List<CountryDO> findCountryList();

    Map<Integer, String> mapCountryListToName();

    Map<String, Integer> mapCountryListToId();

    Map<Integer, String> mapCountryListToCode();

    Map<String, String> mapCountryListToCodeName();

    Map<String, String> mapCountryListToNameCode();


    Map<Long, String> currencyMapId2Name();


    List<CustomerDO> getCustomerInfoByIds(List<Long> ids);

    /**
     * 获取国家信息
     *
     * @param publishPlaceIdED
     * @return
     */
    CountryDO findCountryByName(String publishPlaceIdED);

    /**
     * @param customerName
     * @return
     * @Description 根据客户名称精确查询客户信息
     * @author tanle
     * @since 2019年5月17日
     */
    CustomerDO getCustomerInfoByName(String customerName);

    /**
     * 根据Code获取国家地区详情
     *
     * @param CountryCode
     * @return
     */
    CountryDO findCountryByCode(String CountryCode);

    /**
     * 查询所有客户信息
     *
     * @return
     */
    List<CustomerDO> getCustomerInfoByNameAll();


    /**
     * 查询跟进信息列表
     *
     * @param linkmanDO
     * @return
     */
    List<LinkmanDO> findLinkmanList(Long customerId);

    void pushApp4CaseStatusChange(SyncCaseStatusInfo statusInfo);

    void pushApp4CaseStatusChange2(SyncCaseStatusInfo statusInfo);
}
