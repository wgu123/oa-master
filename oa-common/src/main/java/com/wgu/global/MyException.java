package com.wgu.global;

import lombok.Data;

/**
 * 自定义异常
 * @Author: w
 * @Date: 2019/4/25 11:22
 * @Version 1.0
 */
@Data
public class MyException extends RuntimeException{
    //状态码默认400
    private int code = ResultCode.FAIL;

    public MyException(int code,String msg) {
        super(msg);
        this.code = code;
    }
    public MyException(String msg) {
        super(msg);
    }
}
