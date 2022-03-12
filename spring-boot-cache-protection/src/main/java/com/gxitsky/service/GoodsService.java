package com.gxitsky.service;

import com.gxitsky.entity.Goods;

/**
 * @author gxing
 * @desc 订单
 * @date 2022/3/12
 */
public interface GoodsService {

    Goods get(Long id);

    String getFromCache(Long id);

    int update(Goods goods);
}
