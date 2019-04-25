package com.wgu.modules.user.vo;

import com.wgu.group.SelectGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * @Author: w
 * @Date: 2019/4/25 17:15
 * @Version 1.0
 */
@Getter
@ApiModel(description = "登录对象")
public class LoginVO {
    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空",groups = {SelectGroup.class})
    private String username;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空",groups = {SelectGroup.class})
    private String password;
}
