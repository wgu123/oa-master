package com.wgu.monitor.rest;

import com.wgu.annotations.Log;
import com.wgu.global.Result;
import com.wgu.group.AddGroup;
import com.wgu.group.UpdateGroup;
import com.wgu.monitor.service.RedisService;
import com.wgu.monitor.vo.RedisVo;
import com.wgu.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Redis缓存
 * Created by w on 2019/3/20.
 */
@RestController
@RequestMapping("api/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @Log("查询Redis缓存")
    @GetMapping(value = "/list")
    public Result getRedis(@RequestParam String key, Pageable pageable){
        return Result.ok(redisService.findByKey(key,pageable));
    }

    @Log("新增Redis缓存")
    @PostMapping(value = "/create")
    public Result create(@RequestBody RedisVo resources){
        ValidatorUtils.validateEntity(resources, AddGroup.class);
        redisService.save(resources);
        return Result.ok();
    }

    @Log("修改Redis缓存")
    @PostMapping(value = "/update")
    public Result update(@RequestBody RedisVo resources){
        ValidatorUtils.validateEntity(resources, UpdateGroup.class);
        redisService.save(resources);
        return Result.ok();
    }

    @Log("删除Redis缓存")
    @PostMapping(value = "/delete")
    public Result delete(@RequestParam String key){
        redisService.delete(key);
        return Result.ok();
    }

    @Log("清空Redis缓存")
    @PostMapping(value = "/deleteAll")
    public Result deleteAll(){
        redisService.flushdb();
        return Result.ok();
    }
}
