package com.wgu.modules.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wgu.entity.UserInfo;
import com.wgu.global.MyException;
import com.wgu.jwt.JwtParam;
import com.wgu.jwt.JwtUtils;
import com.wgu.modules.user.dto.UserDTO;
import com.wgu.modules.user.mapper.UserMapper;
import com.wgu.modules.user.repository.UserRepository;
import com.wgu.modules.user.service.AuthService;
import com.wgu.modules.user.vo.LoginVO;
import com.wgu.utils.RequestHolder;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: w
 * @Date: 2019/4/25 17:23
 * @Version 1.0
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final JwtParam jwtParam;
    private final UserMapper userMapper;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, JwtParam jwtParam, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.jwtParam = jwtParam;
        this.userMapper = userMapper;
    }

    @Override
    public JSONObject login(LoginVO loginVO) {
        UserInfo userInfo = userRepository.findByUsernameAndPassword(loginVO.getUsername(),loginVO.getPassword())
                .orElseThrow(()->new MyException(402,"账号或密码不正确"));

        // 验证通过生成token
        String token = JwtUtils.createToken(userInfo.getId()+"", jwtParam);
        if (token == null) {
            log.error("===== 用户签名失败 =====");
            return null;
        }
        JSONObject result = new JSONObject();
        result.put("Authorization",JwtUtils.getAuthorizationHeader(token));
        return result;
    }

    @Override
    public UserDTO getUserInfo() {
        Claims claims=(Claims) RequestHolder.getHttpServletRequest().getAttribute("CLAIMS");
        UserInfo userInfo = null;
        if(claims!=null){
            String userId = (String) claims.get("userId");
            userInfo = userRepository.findById(Long.valueOf(userId)).orElseThrow(()->new RuntimeException("异常"));
        }
        return userMapper.toDto(userInfo);
    }
}
