package com.wp.weipu.security.interceptor;

import com.wp.weipu.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zwp
 */

public class AdminAccessInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AdminAccessInterceptor.class);

    @Autowired
    IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        String path = request.getContextPath();
        String RequestMethod = request.getMethod();
        System.out.println("请求方式" + RequestMethod);
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin,Content-Type,Accept,token,X-Requested-With");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("text/html; charset=UTF-8");
//        response = SetHttpHeaders.setHeaders(request, response);
        if (RequestMethod.equals("OPTIONS")) {
            return true;
        }
        String author = request.getHeader("user");
        if (null == author || author.equals("")) {
            return false;
        }
        logger.info("访问的地址" + url);
        logger.info("path" + path);
        //拦截快开始的一些url
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
