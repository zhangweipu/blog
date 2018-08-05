package com.wp.weipu.test;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 去重复数据
 * @author zwp
 */

public class SingleList<T> {


    public List<T> getSingleList(ArrayList<T> list){
        Set set=new HashSet(list);
        list.clear();
        list.addAll(set);
        return list;
    }

    @Test
    public void test1(){
        List<String> list=null;
        System.out.println(list);
        if (list.isEmpty()){
            System.out.println("aaaaa");
        }
    }
}
