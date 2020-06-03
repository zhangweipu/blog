package com.wp.weipu.test.leetcode;

import org.junit.Test;

/**
 * dns查找缓存,
 * trie树，前缀树
 */
public class DnsCache {
    //ip地址最多有11个不同的字符
    private final int CHARCOUNT = 11;
    private TrieNode root = new TrieNode();

    public int getIndexFromChar(char c) {
        // 根据ascii码计算的吧
        return (c == '.') ? 10 : (c - '0');
    }

    public char getCharFromIndex(int i) {
        return (i == 10) ? '.' : (char) ('0' + i);
    }

    @Test
    public void test() {
//        System.out.println(getCharFromIndex(10));
//        System.out.println(getIndexFromChar('1'));
        assert 1 == 2 : "错了";
    }

    public void insert(String ip, String url) {
        int ipLen = ip.length();
        TrieNode tmp = root;
        for (int i = 0; i < ipLen; i++) {
            int ipNum = getIndexFromChar(ip.charAt(i));
            if (tmp != null) {
                if (tmp.children[ipNum] == null) {
                    tmp.children[ipNum] = new TrieNode();
                }
            }
            //跳转到下一个
            assert tmp != null : "为空了";
            tmp = tmp.children[ipNum];
        }
        tmp.isLeaf = true;
        tmp.url = url;
    }

    public String searchDnsCache(String ip) {
        TrieNode tmp = root;
        int ipLen = ip.length();
        for (int i = 0; i < ipLen; i++) {
            int index = getIndexFromChar(ip.charAt(i));
            if (tmp != null) {
                tmp = tmp.children[index];
            } else {
                return null;
            }
        }
        if (tmp != null && tmp.isLeaf) {
            return tmp.url;
        }
        return null;
    }

    class TrieNode {
        public boolean isLeaf;
        public String url;
        TrieNode[] children;

        public TrieNode() {
            this.isLeaf = false;
            this.url = null;
            this.children = new TrieNode[CHARCOUNT];
            for (int i = 0; i < CHARCOUNT; i++) {
                //这个地方的操作有点疑惑
                children[i] = null;
            }
        }
    }

}
