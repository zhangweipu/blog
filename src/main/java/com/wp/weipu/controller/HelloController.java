package com.wp.weipu.controller;

import com.wp.weipu.common.base.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zwp
 */
@Controller
public class HelloController {
    private static final Logger logger= LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello2")
    public String hello2(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        System.out.println(request.getContextPath());
        System.out.println(request.getRequestURI());
        System.out.println(request.getAuthType());
        System.out.println(request.getCookies());
        System.out.println(request.getDateHeader(""));
        System.out.println(request.getHeader(""));
        System.out.println(request.getMethod());
        System.out.println(request.getHeaderNames());
        System.out.println(request.getParts().toString());
        //System.out.println(request.getSession());
        System.out.println(request.getServletPath());
        System.out.println(request.getContentType());
        System.out.println(request.getServerName());
        System.out.println(request.changeSessionId());






        return "index/hello2";
    }

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("test","hello world");
        return "/index";
    }

    @ResponseBody
    @PostMapping("/admin/api/test")
    public ResultBean test(){
        String path = this.getClass().getResource("").getPath().toString();
        return new ResultBean("success" + path);
    }

    @GetMapping(value = "/security/test")
    public ResultBean security(){
        return new ResultBean("aa");
    }
}
