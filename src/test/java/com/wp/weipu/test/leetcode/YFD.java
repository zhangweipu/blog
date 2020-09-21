package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.Stack;

public class YFD {

    public static String getAns(String s) {
        String[] ss = s.split(" ");
        Stack<String> stack = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        int n = ss.length;
        for (int i = 0; i < n; i++) {
            int res = 0;
            if (!stack.empty() && stack.peek().equals(")")) {
                stack.pop();
                String t = stack.pop();
                int a = nums.pop();
                int b = nums.pop();
                if (t.equals("+")) {
                    res = a + b;
                } else if (t.equals("*")) {
                    res = a * b;
                } else if (t.equals("-")) {
                    res = b - a;
                }
                stack.pop();
                nums.push(res);
            }
            if (ss[i].matches("-?\\d+")) {
                int a = Integer.parseInt(ss[i]);
                nums.push(a);
            } else {
                char[] k = ss[i].toCharArray();
                stack.push(ss[i]);
            }
        }
        //这块有重复代码。。。。。
        while (!stack.empty()) {
            if (stack.peek().equals(")")) {
                stack.pop();
                String t = stack.pop();
                int a = nums.pop();
                int b = nums.pop();
                if (t.equals("+")) {
                    return String.valueOf(a + b);
                } else if (t.equals("*")) {
                    return String.valueOf(a * b);
                } else if (t.equals("-")) {
                    return String.valueOf(b - a);
                }
                stack.pop();
            } else {
                return "invalid";
            }
        }
        return "invalid";
    }

    @Test
    public void test() {
        String ans = getAns("( + 2 ( - 2 2 ) )");
        System.out.println(ans);
    }
}
