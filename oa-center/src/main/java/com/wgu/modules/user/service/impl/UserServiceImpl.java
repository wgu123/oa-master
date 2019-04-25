package com.wgu.modules.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wgu.entity.UserInfo;
import com.wgu.modules.user.repository.UserRepository;
import com.wgu.modules.user.service.UserService;
import com.wgu.modules.user.vo.LoginVO;
import com.wgu.utils.RequestHolder;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @Author: w
 * @Date: 2019/4/25 14:32
 * @Version 1.0
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public void save(UserInfo userInfo) {
        userRepository.save(userInfo);
    }

    @Override
    public UserInfo getUserInfo() {
        Claims claims=(Claims) RequestHolder.getHttpServletRequest().getAttribute("CLAIMS");
        UserInfo userInfo = null;
        if(claims!=null){
            String userId = (String) claims.get("userId");
            userInfo = userRepository.findById(Long.valueOf(userId)).orElseThrow(()->new RuntimeException("异常"));
        }
        return userInfo;
    }
}
