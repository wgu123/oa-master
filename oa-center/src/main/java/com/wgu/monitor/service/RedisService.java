package com.wgu.monitor.service;

import com.alibaba.fastjson.JSONObject;
import com.wgu.monitor.vo.RedisVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author: w
 * @Date: 2019/4/26 9:36
 * @Version 1.0
 */
public interface RedisService {

    /**
     * findByKey
     * @param key
     * @return
     */
    JSONObject findByKey(String key, Pageable pageable);

    /**
     * create
     * @param redisVo
     */
    void save(RedisVo redisVo);

    /**
     * delete
     * @param key
     */
    void delete(String key);

    /**
     * 清空所有缓存
     */
    void flushdb();
}
