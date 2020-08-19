package com.wp.weipu.test.leetcode;

/**
 * 前缀树
 */
public class TrieTree {

    private TrieNode root;

    public TrieTree() {
        root = new TrieNode();
    }

    //插入单词
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            if (!node.containsKey(curr)) {
                node.put(curr, new TrieNode());
            }
            node = node.get(curr);
        }
        node.setEnd();
    }

    /**
     * 用来遍历树的
     *
     * @param word
     * @return
     */
    public TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            if (node.containsKey(curr)) {
                node = node.get(curr);
            } else {
                return null;
            }
        }
        return node;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd;
    }


    /**
     * 前缀树也挺大的吗
     */
    class TrieNode {
        //有很多的分支
        private TrieNode[] links;
        private final int R = 26;
        private boolean isEnd;

        //根呢
        public TrieNode() {
            links = new TrieNode[26];
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }
}
