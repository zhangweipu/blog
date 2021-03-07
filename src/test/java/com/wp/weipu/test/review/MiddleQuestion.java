package com.wp.weipu.test.review;

import java.lang.reflect.Array;
import java.util.*;

public class MiddleQuestion {
    /**
     * 魔术排列
     *
     * @param target
     * @return
     */
    public boolean isMagic(int[] target) {
        int n = target.length;
        int[] nums = new int[n];
        //填充数据
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        nums = magicSort(nums, n);
        int k = getLen(nums, target);
        if (k == 0) {
            return false;
        }
        int numLen = n;
        while (numLen > 0) {
            //取走前k个数
            for (int i = k; i < numLen; i++) {
                //往前提数
                nums[i - k] = nums[i];
                target[i - k] = target[i];
            }
            if (numLen > k) {
                numLen -= k;
            } else {
                numLen = 0;
            }
            if (numLen == 0) {
                return true;
            }
            nums = magicSort(nums, numLen);
            for (int i = 0; i < k && i < numLen; i++) {
                if (nums[i] != target[i]) {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 魔术排列
     *
     * @param nums
     * @return
     */
    public int[] magicSort(int[] nums, int length) {
        int n = length;
        int[] ans = new int[n];
        int mid = n / 2;
        int even = 0, odd = mid;
        for (int i = 0; i < n; i++) {
            if ((i + 1) % 2 == 0) {
                ans[even++] = nums[i];
            } else {
                ans[odd++] = nums[i];
            }
        }
        return ans;
    }

    /**
     * 获取相同数字的长度
     *
     * @param nums
     * @param target
     * @return
     */
    public int getLen(int[] nums, int[] target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != target[i]) {
                return i;
            }
        }
        return n;
    }

    /**
     * 等差数列划分
     * 用暴力的方法
     *
     * @param A
     * @return
     */
    public int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        int count = 0;
        for (int i = 0; i < n - 2; i++) {
            int d = A[i + 1] - A[i];
            for (int j = i + 1; j < n - 1; j++) {
                if (A[j + 1] - A[j] != d) {
                    break;
                } else {
                    count++;
                }
            }
        }
        return count;
    }

    protected class UnionFind {
        private int[] parent;
        //以i为节点的子树的高度（引入了路径压缩以后该定义并不准确）
        private int[] rank;

        //初始化并查集
        public UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                //刚开始，根都是自己
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        int find(int x) {
            if (x != parent[x]) {
                //这里进行了路径压缩
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            //推选一个节点作为父节点
            if (rank[rootX] == rank[rootY]) {
                parent[rootX] = rootY;
                rank[rootY]++;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
            }
        }
    }

    /**
     * 交换字符串中的元素
     *
     * @param s
     * @param pairs
     * @return
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.size() == 0) {
            return s;
        }
        //1、将任意交换的节点对输入并查集
        int len = s.length();
        UnionFind unionFind = new UnionFind(len);
        for (List<Integer> pair : pairs) {
            int x = pair.get(0);
            int y = pair.get(1);
            unionFind.union(x, y);
        }
        //2、构建映射关系
        char[] chars = s.toCharArray();
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            if (map.containsKey(root)) {
                map.get(root).offer(chars[i]);
            } else {
                map.computeIfAbsent(root, k -> new PriorityQueue<>()).offer(chars[i]);
            }
        }
        //3、重建字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            sb.append(map.get(root).poll());
        }
        return sb.toString();
    }
}
