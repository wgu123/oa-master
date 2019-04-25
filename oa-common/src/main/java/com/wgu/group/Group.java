package com.wgu.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 *
 * @author w
 * @date 2019-03-15 23:15
 */
@GroupSequence({AddGroup.class, UpdateGroup.class,SelectGroup.class})
public interface Group {

}
