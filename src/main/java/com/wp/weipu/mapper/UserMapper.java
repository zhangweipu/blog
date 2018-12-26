package com.wp.weipu.mapper;

import com.wp.weipu.common.base.BaseMapper;
import com.wp.weipu.dto.UserDto;
import com.wp.weipu.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> validateUser(UserDto userDto);
}