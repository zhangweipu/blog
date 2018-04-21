package com.wp.weipu.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Map;


@Configuration
@AutoConfigureAfter(MybatisConfiguration.class)
public class MapperConfiguration implements EnvironmentAware {

    private String basePackage;

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(Environment environment){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(basePackage);
      //  mapperScannerConfigurer.setMarkerInterface(LFMapper.class);
        return mapperScannerConfigurer;
    }

    @Override
    public void setEnvironment(Environment env) {

        basePackage = env.getProperty("spring.mybatis.base-package");
    }
}