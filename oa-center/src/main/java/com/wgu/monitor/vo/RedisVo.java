package com.wgu.monitor.vo;

import com.wgu.group.AddGroup;
import com.wgu.group.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: w
 * @Date: 2019/4/26 9:37
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisVo implements Serializable {

    @NotBlank(message = "key不能空",groups = {AddGroup.class, UpdateGroup.class})
    private String key;

    @NotBlank(message = "value不能空",groups = {AddGroup.class, UpdateGroup.class})
    private String value;
}
