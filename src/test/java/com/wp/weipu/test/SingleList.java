package com.wp.weipu.test;

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
}
