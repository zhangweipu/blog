package com.wp.weipu.test.leetcode;

import java.util.Arrays;

/**
 * 跳表的实现
 */
public class SkipList {
    int MAXLEVEL = 16;
    int curLevel;
    Node head;

    public SkipList() {
        //标识最顶层
        curLevel = 1;
        head = new Node(-1);
        head.nextPoint = new Node[MAXLEVEL];
    }

    public boolean search(int target) {
        Node tmp = head;
        //这里从curlevel找不就行了
        for (int i = MAXLEVEL - 1; i >= 0; i--) {
            while (tmp.nextPoint[i] != null && tmp.nextPoint[i].val <= target) {
                if (tmp.nextPoint[i].val == target) {
                    return true;
                } else {
                    tmp = tmp.nextPoint[i];
                }
            }
        }
        if (tmp.nextPoint[0] != null && tmp.nextPoint[0].val == target) {
            return true;
        }
        return false;
    }

    public void add(int num) {
        int level = randomLevel(0.5);
        if (level > curLevel) {
            curLevel = level;
        }
        Node node = new Node(num);
        node.nextPoint = new Node[level];
        // forward前驱节点
        Node[] forward = new Node[level];
        // 每一层都赋上头节点
        Arrays.fill(forward, head);
        // head都是同一个啊
        Node tmp = head;
        //找到前驱节点
        for (int i = level - 1; i >= 0; i--) {
            while (tmp.nextPoint[i] != null && tmp.nextPoint[i].val < num) {
                tmp = tmp.nextPoint[i];
            }
            forward[i] = tmp;
        }
        //跟新链表
        for (int i = 0; i < level; i++) {
            node.nextPoint[i] = forward[i].nextPoint[i];
            forward[i].nextPoint[i] = node;
        }

    }

    public boolean erase(int num) {
        Node[] forward = new Node[curLevel];
        Node tmp = head;
        for (int i = curLevel - 1; i >= 0; i--) {
            while (tmp.nextPoint[i] != null && tmp.nextPoint[i].val < num) {
                tmp = tmp.nextPoint[i];
            }
            forward[i] = tmp;
        }
        boolean res = false;
        if (tmp.nextPoint[0] != null && tmp.nextPoint[0].val == num) {
            res = true;
            for (int i = curLevel - 1; i >= 0; i--) {
                if (forward[i].nextPoint[i] != null && forward[i].nextPoint[i].val == num) {
                    forward[i].nextPoint[i] = forward[i].nextPoint[i].nextPoint[i];
                }
            }
        }
        // 删除孤立节点层
        while (curLevel > 1 && head.nextPoint[curLevel - 1] == null) {
            curLevel -= 1;
        }
        return res;
    }

    /**
     * 随机的产生层
     *
     * @param p
     * @return
     */
    public int randomLevel(double p) {
        int level = 1;
        while (Math.random() < p && level < MAXLEVEL) {
            level++;
        }
        return level;
    }

    class Node {
        int val;
        // 用数组，做什么
        Node[] nextPoint;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        SkipList sk = new SkipList();
        sk.add(1);
        sk.add(2);
        sk.add(3);
        sk.add(4);
        sk.add(5);
        sk.add(6);
    }
}
