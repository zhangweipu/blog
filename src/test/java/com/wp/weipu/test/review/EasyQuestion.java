package com.wp.weipu.test.review;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;

public class EasyQuestion {
    /**
     * 速算机器人
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        int x = 1, y = 0;
        for (char a : s.toCharArray()) {
            switch (a) {
                case 'A':
                    x = 2 * x + y;
                    break;
                case 'B':
                    y = 2 * y + x;
            }
        }
        return x + y;
    }

    /**
     * 早餐组合
     * 使用二分查找降低时间复杂度，还需要先排序,数贼大可如何是好
     * 排序加双指针
     *
     * @param staple
     * @param drinks
     * @param x
     * @return
     */
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int total = 0;
        int i = drinks.length - 1;
        for (int s : staple) {
            int tmp = x - s;
            if (tmp <= 0) {
                break;
            }
            while (i >= 0 && drinks[i] > tmp) {
                i--;
            }
            total += i + 1;
            total %= 1000000007;
        }
        return total;
    }

    /**
     * 不对，有重复的就不好用了
     *
     * @param drinks
     * @param target
     * @return
     */
    public int searchIndex(int[] drinks, int target) {
        int n = drinks.length;
        int mid = n / 2;
        int left = 0, right = n - 1;
        while (left < right) {
            if (drinks[mid] < target) {
                left = mid + 1;
            } else if (drinks[mid] == target) {
                //众数是这个就不行了
                return mid + 1;
            } else {
                right = mid - 1;
            }
        }
        //还用加一的吗
        return right + 1;
    }

    /**
     * 较大分组的位置
     * 连续的字符超过三的就是较大的，记录开始和终止索
     *
     * @param s
     * @return
     */
    public List<List<Integer>> largeGroupPositions(String s) {
        int n = s.length();
        List<List<Integer>> ans = new ArrayList<>();
        int start = 0, end = 0;
        char tag = s.charAt(0);
        for (int i = 1; i < n; i++) {
            if (tag != s.charAt(i)) {
                if (end - start >= 2) {
                    List<Integer> sub = new ArrayList<>();
                    sub.add(start);
                    sub.add(end);
                    ans.add(sub);
                }
                tag = s.charAt(i);
                start = i;
                end = i;
            } else {
                end = i;
            }
        }
        //这一步不应该想不到，代码写的不怎么样啊
        if (end - start >= 2) {
            List<Integer> sub = new ArrayList<>();
            sub.add(start);
            sub.add(end);
            ans.add(sub);
        }
        return ans;
    }

    /**
     * 答案中的
     *
     * @param s
     * @return
     */
    public List<List<Integer>> largeGroupPositions1(String s) {
        int n = s.length();
        List<List<Integer>> ans = new ArrayList<>();
        int num = 1;
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
                //没用脑子！！！
                if (num >= 3) {
                    ans.add(Arrays.asList(i - num + 1, i));
                }
                num = 1;
            } else {
                num++;
            }
        }
        return ans;
    }

    @Test
    public void test78() {
        String s = "aaa";
        System.out.println(JSON.toJSONString(largeGroupPositions(s)));
    }

    /**
     * 传递信息，居然是简单级别的
     *
     * @param n
     * @param relation
     * @param k
     * @return
     */
    public int numWays(int n, int[][] relation, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] rel : relation) {
            if (map.containsKey(rel[1])) {
                map.get(rel[1]).add(rel[0]);
            } else {
                map.computeIfAbsent(rel[1], v -> new ArrayList<>()).add(rel[0]);
            }
        }

        return total(map, k, n - 1);
    }

    /**
     * 递归查询路径的
     *
     * @param map
     * @param k
     * @param n
     * @return
     */
    public int total(Map<Integer, List<Integer>> map, int k, int n) {
        if (k == 0) {
            return n == 0 ? 1 : 0;
        }
        if (map.get(n) == null) {
            return 0;
        }
        int count = 0;
        for (int i : map.get(n)) {
            count += total(map, k - 1, i);
        }
        return count;
    }
}
