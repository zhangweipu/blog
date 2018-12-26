package com.wp.weipu.service;

import com.wp.weipu.dto.UserDto;

/**
 * 用户管理接口
 */
public interface IUserService {
    boolean validateUser(UserDto userDto);
}
