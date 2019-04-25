package com.wgu.annotations;

import java.lang.annotation.*;

/**
 * 忽略token认证
 * @Author: w
 * @Date: 2019/4/25 9:51
 * @Version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PassToken {
    boolean required() default true;
}
