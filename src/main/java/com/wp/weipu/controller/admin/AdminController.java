package com.wp.weipu.controller.admin;

import com.wp.weipu.common.base.ResultBean;
import com.wp.weipu.dto.UserDto;
import com.wp.weipu.entity.User;
import com.wp.weipu.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    public static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    IUserService userService;

    @PostMapping(value = "/login")
    public ResultBean<User> login(UserDto userDto) {
        logger.info("信息验证");
        ResultBean result = new ResultBean();
        if (userService.validateUser(userDto)) {
            logger.info("验证成功！！");
            result.setCode(1);
            result.setMsg("验证成功");
        } else {
            logger.info("验证失败！！");
            result.setCode(0);
            result.setMsg("用户名或密码不正确");
        }
        return new ResultBean<>();
    }

}
