package com.wp.weipu.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 转化成map的操作
 *
 * @author zwp
 */

public class ConverToMapTest<T> {
    private static final Logger logger = LoggerFactory.getLogger(ConverToMapTest.class);

    /**
     * public static <T>
     *
     * @param key       key值
     * @param t         集合中的对象
     * @param targetMap 生成的map
     * @return
     */
    public static <T> Map<String, List<T>> convertMap(String key, T t, Map<String, List<T>> targetMap) {
        if (null == targetMap.get(key)) {
            List<T> targetList = new ArrayList<>();
            targetList.add(t);
            targetMap.put(key, targetList);
        } else {
            targetMap.get(key).add(t);
        }
        return targetMap;
    }

    public static void toMap() {
    }

}
