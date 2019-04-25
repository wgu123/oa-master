package com.wgu.global;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 自定义响应结构
 * @Author: w
 * @Date: 2019/4/25 11:13
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@JSONType(serialzeFeatures={SerializerFeature.NotWriteDefaultValue, SerializerFeature.WriteMapNullValue})
public class Result<T> implements Serializable {
    public final static Integer CODE_SUCCESS = ResultCode.SUCCESS;
    public final static String MSG_SUCCESS = "请求成功";
    public final static String MSG_FAIL = "请求失败";

    //默认状态码200
    @JSONField(ordinal = 1)
    private int code;
    //默认返回信息
    @JSONField(ordinal = 2)
    private  String msg;
    @JSONField(ordinal = 3)
    private T data;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime timestamp= LocalDateTime.now();

    public static <T> Result<T> ok(T data) {
        return new Result(data);
    }

    public static <T> Result<T> ok() {
        return new Result(CODE_SUCCESS, MSG_SUCCESS);
    }

    public Result(T data) {
        this.code = CODE_SUCCESS;
        this.msg = MSG_SUCCESS;
        this.data = data;
    }
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
