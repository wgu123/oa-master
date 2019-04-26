package com.wgu.monitor.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wgu.monitor.service.RedisService;
import com.wgu.monitor.vo.RedisVo;
import com.wgu.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: w
 * @Date: 2019/4/26 9:38
 * @Version 1.0
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    JedisPool pool;

    @Override
    public JSONObject findByKey(String key, Pageable pageable){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            List<RedisVo> redisVos = new ArrayList<>();

            if(!key.equals("*")){
                key = "*" + key + "*";
            }
            for (String s : jedis.keys(key)) {
                RedisVo redisVo = new RedisVo(s,jedis.get(s));
                redisVos.add(redisVo);
            }
            Page<RedisVo> page = new PageImpl<RedisVo>(
                    PageUtils.toPage(pageable.getPageNumber(),pageable.getPageSize(),redisVos),
                    pageable,
                    redisVos.size());
            return PageUtils.resultWrap(page);
        }finally{
            if(null != jedis){
                jedis.close(); // 释放资源还给连接池
            }
        }

    }

    @Override
    public void save(RedisVo redisVo) {
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.set(redisVo.getKey(),redisVo.getValue());
        }finally{
            if(null != jedis){
                jedis.close(); // 释放资源还给连接池
            }
        }
    }

    @Override
    public void delete(String key) {
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.del(key);
        }finally{
            if(null != jedis){
                jedis.close(); // 释放资源还给连接池
            }
        }

    }

    @Override
    public void flushdb() {
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.flushDB();
        }finally{
            if(null != jedis){
                jedis.close(); // 释放资源还给连接池
            }
        }

    }
}
