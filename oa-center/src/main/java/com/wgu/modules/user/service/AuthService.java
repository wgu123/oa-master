package com.wgu.modules.user.service;

import com.alibaba.fastjson.JSONObject;
import com.wgu.modules.user.vo.LoginVO;

/**
 * @Author: w
 * @Date: 2019/4/25 17:22
 * @Version 1.0
 */
public interface AuthService {
    /**
     * 用户登录
     * @param loginVO
     * @return
     */
    JSONObject login(LoginVO loginVO);
}
