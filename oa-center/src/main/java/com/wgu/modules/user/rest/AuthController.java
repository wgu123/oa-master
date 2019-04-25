package com.wgu.modules.user.rest;

import com.wgu.annotations.Log;
import com.wgu.annotations.PassToken;
import com.wgu.annotations.UserLoginToken;
import com.wgu.global.Result;
import com.wgu.group.SelectGroup;
import com.wgu.modules.user.service.AuthService;
import com.wgu.modules.user.vo.LoginVO;
import com.wgu.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录及获取用户信息
 * @Author: w
 * @Date: 2019/4/25 16:56
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = "登录模块", description = "此模块可登录 可获取用户信息")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 登录
    @Log("用户登录")
    @PostMapping("/login")
    @PassToken // 加此注解, 请求不做token验证
    public Result login(@RequestBody LoginVO loginVO) {
        ValidatorUtils.validateEntity(loginVO, SelectGroup.class);
        return Result.ok(authService.login(loginVO));
    }

    // 获取用户信息
    @Log("获取用户信息")
    @GetMapping("/getUserInfo")
    @UserLoginToken // 加此注解，必须进行token验证
    public Result getUserInfo(){
        return Result.ok(authService.getUserInfo());
    }
}
