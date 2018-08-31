package com.wp.weipu.controller.applets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试微信小程序调用后台
 * @author zwp
 */
@RestController
@RequestMapping(value = "/itime")
public class Itime {
    private static final Logger logger= LoggerFactory.getLogger(Itime.class);

    @Value("${server.port}")
    private String data;

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String testItime(@RequestBody String name){
        logger.error(name);
        System.out.printf(data+"______________________________");
        return "you are success";
    }
}
