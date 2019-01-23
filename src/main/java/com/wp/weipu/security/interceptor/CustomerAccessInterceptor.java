package com.wp.weipu.security.interceptor;

import com.wp.weipu.common.utils.SetHttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
public class CustomerAccessInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(CustomerAccessInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("here");
        SetHttpHeaders.setHeaders(request, response);
        String url = request.getRequestURI();
        logger.info(url);
        logger.info("请求");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
