package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 奇安信
 */
public class QAX {

    public void getStr(String str) {
        String[] strs = str.trim().split("\\s+|\\t");
        LinkedList<String> set = new LinkedList<>();
        //计算两个相邻的undo和redo之间有几个词
        int count = 0;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals("undo")) {
                count = 0;
                set.removeLast();
            } else if (strs[i].equals("redo")) {
                int index = i - 1;
                while (index >= 0) {
                    if (index - 1 >= 0 && strs[index].equals("undo")) {
                        break;
                    }
                    index--;
                }
                int num = set.size() - count;
                //在第几个插入不好计算
                set.add(num, strs[index - 1]);
            } else {
                count++;
                set.addLast(strs[i]);
            }
        }
        for (String s : set) {
            System.out.println(s);
        }
    }

    @Test
    public void test() {
        getStr("word undo");
    }

    public int getTimes(int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += getTimes(n - i);
        }
        return sum;
    }

    public int calulateMethod(int money) {
        if (money < 1) {
            return 0;
        }
        int[] dp = new int[money + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[money];
    }

    @Test
    public void test2() {
        IntStream.range(7,20).boxed().forEach(x->System.out.println(getTimes(x) + "---" + calulateMethod(x)));

    }
}
