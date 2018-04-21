package com.wp.weipu;

import com.wp.weipu.entity.Demo;
import com.wp.weipu.mapper.DemoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.AccessType;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeipuApplicationTests {

	@Autowired
	DemoMapper demoMapper;

	@Test
	public void contextLoads() {
		Demo demo=demoMapper.selectByPrimaryKey(1);
		System.out.println(demo.toString());
	}

}
