package com.wp.weipu.common;

import com.wp.weipu.mapper.DemoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zwp
 * @version 1.0
 * @description: 在静态方法中使用注解
 * @date 2018/4/30
 */
@Component
public class TestUtil {
    private static final Logger logger= LoggerFactory.getLogger(TestUtil.class);

    @Autowired
    private DemoMapper demoMapper;

    private static DemoMapper demo;

    @PostConstruct
    private void init(){
        demo=demoMapper;
    }

    public static void test(){
        demo.selectByPrimaryKey(1);
    }

}
