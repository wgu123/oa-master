package com.wgu.modules.user.rest;

import com.wgu.annotations.PassToken;
import com.wgu.global.Result;
import com.wgu.jwt.JwtParam;
import com.wgu.jwt.JwtUtils;
import com.wgu.modules.user.service.AuthService;
import com.wgu.modules.user.service.UserService;
import com.wgu.modules.user.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录及获取用户信息
 * @Author: w
 * @Date: 2019/4/25 16:56
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    final
    AuthService authService;

    @Autowired
    public AuthController(UserService userService, JwtParam jwtParam, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    // 登录
    @PostMapping("/login")
    @PassToken // 加此注解, 请求不做token验证
    public Result login(@RequestBody LoginVO loginVO) {
        return Result.ok(authService.login(loginVO));
    }
}
