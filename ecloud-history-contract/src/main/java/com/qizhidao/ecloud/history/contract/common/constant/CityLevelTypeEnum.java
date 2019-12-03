/**
 * Copyright © 2018, ZhiYun Network Co.,Ltd. All rights reserved. ZhiYun PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package com.qizhidao.ecloud.history.contract.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: com.qizhidao.ecloud.history.contract.constant.CityLevelTypeEnum
 *
 * Description: ${DESCRIPTION}
 *
 * Date: 2018-12-08
 *
 * @author James
 * @version 1.0.0
 */
@Getter
@AllArgsConstructor
public enum CityLevelTypeEnum {

    /**
     *
     */
    province(1), city(2), area(3);

    private Integer levelType;

}
