package com.wgu.jwt;

import com.wgu.annotations.PassToken;
import com.wgu.annotations.UserLoginToken;
import com.wgu.global.MyException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * jwt 拦截器
 * @Author: w
 * @Date: 2019/4/25 15:04
 * @Version 1.0
 */
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtParam jwtParam;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //获取请求头
        final String authHeader = request.getHeader(JwtConstant.AUTH_HEADER_KEY);
        Claims claims = getClaims(authHeader);
        // 传递所需信息
        request.setAttribute("CLAIMS", claims);

        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()&&claims==null) {
                log.info("===== 用户未登录, 请先登录 =====");
                throw new MyException(401,"未登录");
            }
        }
        return true;
    }

    /**
     * 获取token携带信息
     * @param authHeader
     * @return
     */
    private Claims getClaims(String authHeader){
        if (StringUtils.isEmpty(authHeader)) {
            return null;
        }
        // 校验头格式校验
        if (!JwtUtils.validate(authHeader)) {
            log.info("===== 用户未登录, 请先登录 =====");
            return null;
        }
        // token解析
        final String authToken = JwtUtils.getRawToken(authHeader);
        Claims claims = JwtUtils.parseToken(authToken, jwtParam.getBase64Secret());
        return claims;
    }
}
