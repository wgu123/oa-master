package com.wgu.modules.user.rest;

import com.wgu.annotations.PassToken;
import com.wgu.jwt.JwtParam;
import com.wgu.jwt.JwtUtils;
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
    private final JwtParam jwtParam;

    @Autowired
    public AuthController(UserService userService, JwtParam jwtParam) {
        this.userService = userService;
        this.jwtParam = jwtParam;
    }

    // 登录
    @PostMapping("/login")
    @PassToken // 加此注解, 请求不做token验证
    public String login(@RequestBody LoginVO loginVO) {
        // 1.用户密码验证我这里忽略, 假设用户验证成功, 取得用户id为5
        Integer userId = 5;
        // 2.验证通过生成token
        String token = JwtUtils.createToken(userId + "", jwtParam);
        if (token == null) {
            log.error("===== 用户签名失败 =====");
            return null;
        }
        log.info("===== 用户{}生成签名{} =====", userId, token);
        return JwtUtils.getAuthorizationHeader(token);
    }
}
