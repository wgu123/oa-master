package com.wgu.modules.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by w on 2019/4/25.
 */
@Data
@NoArgsConstructor
public class UserDTO{
    private Long id;
    private String username;
    private String password;
}
