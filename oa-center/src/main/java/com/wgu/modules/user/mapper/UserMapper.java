package com.wgu.modules.user.mapper;

import com.wgu.entity.UserInfo;
import com.wgu.mapper.EntityMapper;
import com.wgu.modules.user.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Created by w on 2019/4/25.
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<UserDTO, UserInfo> {
}
