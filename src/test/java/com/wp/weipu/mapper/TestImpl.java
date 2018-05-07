package com.wp.weipu.mapper;

import com.wp.weipu.entity.Demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author zwp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestImpl {

    @Autowired
    DemoMapper demoMapper;

    @Test
    public void test1(){
        List<Demo> s=demoMapper.selectAll();
        System.out.println(s);
    }

}
