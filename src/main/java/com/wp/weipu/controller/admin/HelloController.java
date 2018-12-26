package com.wp.weipu.controller.admin;

import com.wp.weipu.common.base.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/hello2")
    public String hello2() throws IOException, ServletException {

//        System.out.println(request.getContextPath());
//        System.out.println(request.getRequestURI());
//        System.out.println(request.getAuthType());
//        System.out.println(request.getCookies());
//        System.out.println(request.getDateHeader(""));
//        System.out.println(request.getHeader(""));
//        System.out.println(request.getMethod());
//        System.out.println(request.getHeaderNames());
//        System.out.println(request.getParts().toString());
//        //System.out.println(request.getSession());
//        System.out.println(request.getServletPath());
//        System.out.println(request.getContentType());
//        System.out.println(request.getServerName());
//        System.out.println(request.changeSessionId());
        return "index/hello2";
    }

    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("test","hello world");
        return "index";
    }
}
