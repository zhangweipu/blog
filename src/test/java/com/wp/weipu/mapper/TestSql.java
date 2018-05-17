package com.wp.weipu.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @description:实验在Java中写sql查询
 * @author zwp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestSql {

    @Autowired
    DemoMapper demoMapper;

    public String getStr(){
        return new SQL()
                .SELECT("name")
                .FROM("DEMO")
                .WHERE("ID='1'")
                .toString();
    }

    @Test
    public void testStr(){
        String str=demoMapper.selectName(getStr());
        System.out.println(str);
    }
}
