package com.gxitsky.mapper;

import com.gxitsky.entity.Goods;
import org.springframework.stereotype.Repository;

/**
 * @author gxing
 * @desc 商品
 * @date 2022/3/12
 */
@Repository
public interface GoodsMapper {
    int update(Goods goods);
}
