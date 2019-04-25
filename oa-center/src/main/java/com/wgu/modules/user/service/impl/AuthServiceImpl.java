package com.wgu.modules.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wgu.modules.user.repository.UserRepository;
import com.wgu.modules.user.service.AuthService;
import com.wgu.modules.user.vo.LoginVO;
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
    @Autowired
    UserRepository userRepository;

    @Override
    public JSONObject login(LoginVO loginVO) {

        return null;
    }
}
