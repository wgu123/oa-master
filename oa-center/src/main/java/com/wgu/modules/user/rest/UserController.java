package com.wgu.modules.user.rest;

import com.wgu.annotations.PassToken;
import com.wgu.annotations.UserLoginToken;
import com.wgu.entity.UserInfo;
import com.wgu.jwt.JwtParam;
import com.wgu.jwt.JwtUtils;
import com.wgu.modules.user.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: w
 * @Date: 2019/4/25 14:34
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final JwtParam jwtParam;

    @Autowired
    public UserController(UserService userService, JwtParam jwtParam) {
        this.userService = userService;
        this.jwtParam = jwtParam;
    }

    @PostMapping("/save")
    public String save(@RequestBody UserInfo userInfo){
        userService.save(userInfo);
        return "OK";
    }

    // 登录
    @PostMapping("/login")
    @PassToken // 加此注解, 请求不做token验证
    public String login() {
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

    @GetMapping("/get")
    public void get(HttpServletRequest request){
        userService.getUserInfo();
        log.info("claims");
    }

    @GetMapping("/test")
    @PassToken
    public void test(HttpServletRequest request){
        Claims claims=(Claims) request.getAttribute("CLAIMS");
        log.info("claims");
    }

    @UserLoginToken
    @GetMapping("/test1")
    public void test2(){
        log.info("222");
    }
}
