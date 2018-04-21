package com.wp.weipu.controller;

import com.wp.weipu.common.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zwp
 */
@RestController
public class HelloController {
    private static final Logger logger= LoggerFactory.getLogger(HelloController.class);

    @PostMapping("/hello")
    public ResultBean hello(){
        return new ResultBean("ssss");
    }

    @PostMapping("/admin/api/test")
    public ResultBean test(){
        return new ResultBean("success");
    }

    @GetMapping(value = "/security/test")
    public ResultBean security(){
        return new ResultBean("aa");
    }
}
