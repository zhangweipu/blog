package com.wp.weipu.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zwp
 */

public class MapTest {

    @Test
    public void test1(){
        Map<String,String> map=new HashMap<>();
        if (null==map.get("1")){
            System.out.printf("可以");
        }
    }
}
