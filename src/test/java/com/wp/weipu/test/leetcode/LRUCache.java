package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 一般不会考虑这种实现
 */
public class LRUCache extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        if (super.get(key) != null) {
            int value = super.remove(key);
            super.put(key, value);
            return value;
        }
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        if (capacity < super.size()) {
            //删除第一个，链表头部作为最久没被使用的。。。
            super.remove(super.entrySet().iterator().next());
        }
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }


}
