package com.wp.weipu.controller;

import com.wp.weipu.common.Result;
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
    public Result hello(){
        return new Result("ssss");
    }

    @PostMapping("/admin/api/test")
    public Result test(){
        return new Result("success");
    }

    @GetMapping(value = "/security/test")
    public Result security(){
        return new Result("aa");
    }
}
