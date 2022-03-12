package com.gxitsky.service.impl;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.gxitsky.commons.constants.CacheConstants;
import com.gxitsky.commons.utils.CacheUtil;
import com.gxitsky.commons.utils.LockUtil;
import com.gxitsky.entity.Goods;
import com.gxitsky.mapper.GoodsMapper;
import com.gxitsky.service.GoodsService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gxing
 * @desc 商品
 * @date 2022/3/12
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    private static final String CACHE_PREFIX = "GOODS:";

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * @param id ID
     * @desc 获取
     * @desc 加分布式锁来解决缓存与数据库双写时的数据一致性问题
     * @author gxing
     * @date 2022/3/12 15:47
     */
    @Override
    public Goods get(Long id) {
        String key = CACHE_PREFIX + id;
        // 缓存中不存在去DB查,加分布式锁解决缓存与数据库双写时的数据一致性问题
        boolean lock = LockUtil.tryLock(key);
        Goods goods = null;
        try {
            if (lock) {
                goods = getFromDB(id);
                if (ObjectUtils.isNotEmpty(goods)) {
                    // 更新到缓存
                    CacheUtil.set(key, goods);
                } else {
                    // 数据库不存在, 设置一个默认值
                    CacheUtil.set(key, CacheConstants.CACHE_NULL_DEFAULT, 60L);
                }
            } else {
                Thread.sleep(100);
                get(id);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LockUtil.unlock(key);
        }
        return goods;
    }

    private static final Funnel<CharSequence> charSequenceFunnel = Funnels.stringFunnel(Charsets.UTF_8);
    private static final BloomFilter<CharSequence> bloomFilter = BloomFilter.create(charSequenceFunnel, 1000000, 0.03);

    public void bloomFilterPut(String key) {
            bloomFilter.put(key);
    }

    public boolean bloomFilterContain(String key) {
        return bloomFilter.mightContain(key);
    }

    /**
     * @param goods
     * @desc 新增
     * @desc 加分布式锁来解决缓存与数据库双写时的数据一致性问题
     * @author gxing
     * @date 2022/3/12 17:02
     */
    @Override
    public int update(Goods goods) {
        String key = CACHE_PREFIX + goods.getId();
        LockUtil.tryLock(key);
        try {
            // 先删除缓存
            CacheUtil.delete(key);
            // 后更新数据库
            return goodsMapper.update(goods);
        } finally {
            LockUtil.unlock(key);
        }
    }


    public Goods getFromDB(Long id) {
        return null;
    }

    @Override
    public String getFromCache(Long id) {
        return null;
    }
}
