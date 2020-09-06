package com.wp.weipu.test.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class HW {

    /**
     * 最多可以喝多少水问题
     *
     * @param n
     * @return
     */
    public int getDrinkNum(int n) {
        if (n == 3 || n == 2) {
            return 1;
        }
        if (n == 1) {
            return 0;
        }
        int sum = 0;
        int sub = n / 3;
        sum += sub + getDrinkNum(n % 3 + sub);
        return sum;
    }

    @Test
    public void test1() {
        System.out.println(getDrinkNum(81));
    }

    /**
     * 排序加去重
     *
     * @param arr
     * @return
     */
    public Set<Integer> sortAndDup(int[] arr) {
        Arrays.sort(arr);
        HashSet<Integer> set = new HashSet<>();
        for (int n : arr) {
            set.add(n);
        }
        return set;
    }

    /**
     * 十六进制转十进制
     * 我想错了啊
     *
     * @param hex
     */
    public void changTo10(String hex) {
        char[] a = hex.toCharArray();
        int i = a.length - 1;
        //进位
        int ten = 0;
        StringBuilder sb = new StringBuilder("");

        while (i >= 0 && a[i] != 'x') {
            int curNum = 0;
            if (a[i] >= '0' && a[i] <= '9') {
                curNum = a[i];
            } else if (a[i] >= 'A' && a[i] <= 'F') {
                //字母转化成十六进制数
                curNum = a[i] - 64 + 9;
            }
            ten = ten * 16 + curNum;
            //note:十六进制原来不是这么算的
            //            int n = ten + curNum;
//            if (n >= 10) {
//                sb.append(n % 10);
//                ten = n / 10;
//            } else {
//                ten = 0;
//            }
            i--;
        }

        System.out.println(ten);
    }

    @Test
    public void test4() {
        changTo10("0xFFF");
    }

    @Test
    public void test5() {
        //十进制转二进制
        StringBuilder sb = new StringBuilder("");
        int n = 15;
        while (n > 0) {
            sb.append(n % 2);
            n = n / 2;
        }
        System.out.println(sb.reverse());
    }

    class LRUCache2 {

        private LinkedHashMap<String, Integer> cache;
        private int k;

        public LRUCache2(int k) {
            this.k = k;
            this.cache = new LinkedHashMap<>();
        }

        public void set(String key, int val) {
            //如果有重复的，放到最前
            if (cache.containsKey(key)) {
                cache.remove(key);
                cache.put(key, val);
                return;
            }
            cache.put(key, val);
            if (cache.size() > this.k) {
                //利用迭代器删除第一个
                cache.remove(cache.entrySet().iterator().next().getKey());
            }
        }

        public Integer get(String key) {
            if (!cache.containsKey(key)) {
                return null;
            }
            int res = cache.remove(key);
            cache.put(key, res);
            return res;
        }

    }

    static class LRUCache {

        private Map<String, Node> cache = null;
        private int capacity;
        private Node head;
        private Node tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>();
            //初始化链表头部
            this.head = new Node("head", -1);
            this.tail = new Node("tail", -1);
            this.head.next = this.tail;
            this.tail.pre = this.head;
        }

        public void set(String key, int val) {
            if (cache.containsKey(key)) {
                //移到链表头部
                Node cur = cache.get(key);
                remove(cur);
                addNode(cur);
                return;
            }
            Node newNode = new Node(key, val);
            cache.put(key, newNode);
            addNode(newNode);
            if (cache.size() > capacity) {
                remove(tail.pre);
                cache.remove(tail.pre.key);
            }
        }

        public Integer get(String key) {
            if (!cache.containsKey(key)) {
                return null;
            }
            Node cur = cache.get(key);
            remove(cur);
            addNode(cur);
            return cur.val;
        }

        private void addNode(Node cur) {
            cur.next = head.next;
            head.next.pre = cur;
            head.next = cur;
            cur.pre = head;
        }

        private void remove(Node cur) {
            Assert.assertNotNull("对象为空！！", cur);
            Node tmp = cur.pre;
            tmp.next = cur.next;
            cur.next.pre = tmp;
            cur.next = null;
            cur.pre = null;
        }

        class Node {
            String key;
            int val;
            Node pre;
            Node next;

            public Node(String key, int val) {
                this.val = val;
                this.key = key;
            }
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);
        cache.set("aa", 1);
        cache.set("bb", 1);
        cache.set("cc", 1);
        cache.set("bb", 1);
        cache.set("ss", 1);
    }

}

