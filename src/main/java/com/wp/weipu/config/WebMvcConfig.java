package com.wp.weipu.config;

import com.wp.weipu.security.interceptor.AdminAccessInterceptor;
import com.wp.weipu.security.interceptor.CustomerAccessInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author zwp
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminAccessInterceptor())
                .addPathPatterns("/admin/**");

        registry.addInterceptor(customerAccessInterceptor())
                .addPathPatterns("/customer/**")
                .addPathPatterns("/single/**");
        super.addInterceptors(registry);
    }

    @Bean
    protected AdminAccessInterceptor adminAccessInterceptor() {
        return new AdminAccessInterceptor();
    }

    @Bean
    protected CustomerAccessInterceptor customerAccessInterceptor() {
        return new CustomerAccessInterceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        super.addResourceHandlers(registry);
    }
}
