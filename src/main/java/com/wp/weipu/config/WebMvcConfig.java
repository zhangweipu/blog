package com.wp.weipu.config;

import com.wp.weipu.security.interceptor.AdminAccessInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author zwp
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport{
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminAccessInterceptor())
                .addPathPatterns("/admin/api/**");
        super.addInterceptors(registry);
    }

    @Bean
    protected AdminAccessInterceptor adminAccessInterceptor(){
        return new AdminAccessInterceptor();
    }
}
