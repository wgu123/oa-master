package com.wgu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: w
 * @Date: 2019/4/25 14:26
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "user_info")
@NoArgsConstructor
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
}
