package com.gxitsky.controller;

import com.alibaba.fastjson.JSON;
import com.gxitsky.ResponseResult;
import com.gxitsky.commons.constants.CacheConstants;
import com.gxitsky.entity.Goods;
import com.gxitsky.service.GoodsService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gxing
 * @desc 商口
 * @date 2022/3/12
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/get")
    public ResponseResult<?> get(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return ResponseResult.err("商品不能为空！");
        }
        // 先查缓存
        String result = goodsService.getFromCache(id);
        // 缓存存在
        if (StringUtils.isNotBlank(result)) {
            if (CacheConstants.CACHE_NULL_DEFAULT.equals(result)) {
                return ResponseResult.ok();
            } else {
                Goods goods = JSON.parseObject(result, Goods.class);
                return ResponseResult.ok(goods);
            }
        }
        Goods goods = goodsService.get(id);
        return ResponseResult.ok(goods);
    }

    @GetMapping("/update")
    public ResponseResult<?> update(Goods goods) {
        if (ObjectUtils.isEmpty(goods)) {
            return ResponseResult.err("商品不存在！");
        }
        goodsService.update(goods);
        return ResponseResult.ok();
    }
}
