package com.wp.weipu.mapper;

import com.wp.weipu.entity.City;
import com.wp.weipu.entity.Demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zwp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItTestImpl {

    @Autowired
    CityMapper demoMapper;

    @Test
    public void test1(){
        List<City> s=demoMapper.selectAll();

        List<City> res=new ArrayList<>();
        Iterator iterator=s.iterator();
        while (iterator.hasNext()){
            City c= (City) iterator.next();
            if("-1".equals(c.getPid())){

            }
        }

        System.out.println(s);
    }

}
