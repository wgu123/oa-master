package com.wgu.modules.user.repository;

import com.wgu.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author: w
 * @Date: 2019/4/25 14:33
 * @Version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUsernameAndPassword(String username,String password);
}
