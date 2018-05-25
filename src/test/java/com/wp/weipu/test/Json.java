package com.wp.weipu.test;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wp.weipu.entity.Demo;
import org.junit.Test;

import java.io.IOException;

/**
 * @author zwp
 */

public class Json {

    @Test
    public void test() throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        String str="{\"id\":\"11\",\"name\":\"tom\"}";
        Demo d=mapper.readValue(str,Demo.class);
        //String to json
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println(d.toString());
    }
}
