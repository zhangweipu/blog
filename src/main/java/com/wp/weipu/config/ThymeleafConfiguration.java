package com.wp.weipu.config;

import org.apache.commons.codec.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafConfiguration {

    @SuppressWarnings("unused")
    private final Logger log = LoggerFactory.getLogger(ThymeleafConfiguration.class);

    @Bean
    public ClassLoaderTemplateResolver emailTemplateResolver() {
        ClassLoaderTemplateResolver TemplateResolver = new ClassLoaderTemplateResolver();
        TemplateResolver.setPrefix("/templates/");
        TemplateResolver.setSuffix(".html");
        TemplateResolver.setTemplateMode("HTML5");
        TemplateResolver.setCharacterEncoding(CharEncoding.UTF_8);
        TemplateResolver.setOrder(1);
        return TemplateResolver;
    }
}
