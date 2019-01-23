package com.wp.weipu.service.impl;

import com.wp.weipu.dto.UserDto;
import com.wp.weipu.entity.User;
import com.wp.weipu.mapper.UserMapper;
import com.wp.weipu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 验证用户信息
     *
     * @param userDto
     * @return
     */
    @Override
    public boolean validateUser(UserDto userDto) {
        User users = userMapper.validateUser(userDto);
        if (users == null) {
            return false;
        } else {
            return true;
        }
    }
}
