package com.wp.weipu.controller;

import com.wp.weipu.common.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zwp
 */
@Controller
public class HelloController {
    private static final Logger logger= LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello2")
    public String hello2(){
        return "index/hello2";
    }

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("test","hello world");
        return "index/index";
    }

    @ResponseBody
    @PostMapping("/admin/api/test")
    public ResultBean test(){
        return new ResultBean("success");
    }

    @GetMapping(value = "/security/test")
    public ResultBean security(){
        return new ResultBean("aa");
    }
}
