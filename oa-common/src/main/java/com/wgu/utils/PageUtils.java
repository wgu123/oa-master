package com.wgu.utils;

import cn.hutool.core.util.PageUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页工具
 * Created by w on 2019/3/20.
 */
public class PageUtils extends PageUtil {

    /**
     * List 分页
     * @param page
     * @param size
     * @param list
     * @return
     */
    public static List toPage(int page, int size , List list) {
        int fromIndex = page * size;
        int toIndex = page * size + size;

        if(fromIndex > list.size()){
            return new ArrayList();
        } else if(toIndex >= list.size()) {
            return list.subList(fromIndex,list.size());
        } else {
            return list.subList(fromIndex,toIndex);
        }
    }

    /**
     * 将结果为Page的对象封装成JSON格式返回
     * @return
     */
    static public JSONObject resultWrap(int size , List list,long count){
        JSONObject result = new JSONObject();
        result.put("content",list);
        result.put("pages",0);
        result.put("total",count);
        if(list==null||list.size()==0) return result;
        long pages = count/size + 1;
        result.put("pages",pages);
        return  result;
    }

    /**
     * 将结果为Page的对象封装成JSON格式返回
     * @return
     */
    static public JSONObject resultWrap(Page pageResult){
        if(pageResult==null) return null;
        JSONObject result = new JSONObject();
        result.put("content",pageResult.getContent());
        result.put("pages",pageResult.getTotalPages());
        result.put("total",pageResult.getTotalElements());
        return  result;
    }
}
