package com.wgu.modules.user.service;

import com.wgu.entity.UserInfo;

/**
 * @Author: w
 * @Date: 2019/4/25 14:31
 * @Version 1.0
 */
public interface UserService {

    void save(UserInfo userInfo);

    UserInfo getUserInfo();
}
