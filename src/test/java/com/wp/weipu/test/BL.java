package com.wp.weipu.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BL {
    public boolean IsValidExp(String s) {
        // write code here
        int len = s.length();
        if (len % 2 != 0) {
            return false;
        }
        int top = 0;
        char[] stack = new char[len];
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack[top++] = c;
            } else {
                top--;
                if (c == ')' && stack[top] != '(') {
                    return false;
                }
                if (c == '}' && stack[top] != '{') {
                    return false;
                }
                if (c == ']' && stack[top] != '[') {
                    return false;
                }
            }
        }
        return top == 0;
    }

    @Test
    public void test() {
        String s = "{{}}";
        System.out.println(IsValidExp(s));
    }

    public boolean Game24Points(int[] arr) {
        // write code here
        return true;
    }

    public boolean getNum(int sub, int[] arr, int[] colored, int num) {
        if (sub == 24) {
            return true;
        }
        boolean flag=false;
        switch (num) {
            //加
            case 1:
                for (int j = 0; j < 4; j++) {
                    if (colored[j] == 0) {
                        colored[j] = j;
                         flag = getNum(sub + arr[j], arr, colored, 1) ||
                                getNum(sub + arr[j], arr, colored, 2) ||
                                getNum(sub + arr[j], arr, colored, 3) ||
                                getNum(sub + arr[j], arr, colored, 4);
                        colored[j] = 0;
                    }
                }
                break;
            case 2:
                for (int j = 0; j < 4; j++) {
                    if (colored[j] == 0) {
                        colored[j] = j;
                         flag =getNum(sub - arr[j], arr, colored, 1) ||
                                getNum(sub - arr[j], arr, colored, 2) ||
                                getNum(sub - arr[j], arr, colored, 3) ||
                                getNum(sub - arr[j], arr, colored, 4);
                        colored[j] = 0;
                    }
                }
                break;
            case 3:
                for (int j = 0; j < 4; j++) {
                    if (colored[j] == 0) {
                        colored[j] = j;
                         flag = getNum(sub * arr[j], arr, colored, 1) ||
                                getNum(sub * arr[j], arr, colored, 2) ||
                                getNum(sub * arr[j], arr, colored, 3) ||
                                getNum(sub * arr[j], arr, colored, 4);
                        colored[j] = 0;
                    }
                }
                break;
            case 4:
                for (int j = 0; j < 4; j++) {
                    if (colored[j] == 0) {
                        colored[j] = j;
                        if (arr[j] != 0) {
                            sub = sub / arr[j];
                             flag = getNum(sub, arr, colored, 1) ||
                                    getNum(sub, arr, colored, 2) ||
                                    getNum(sub, arr, colored, 3) ||
                                    getNum(sub, arr, colored, 4);
                            colored[j] = 0;
                        }
                    }
                }
                break;
        }
        return flag;
    }

    public int GetCoinCount(int N) {
        int sub = 1024 - N;
        int count = 0;
        if (sub >= 64) {
            count += sub / 64;
            sub = sub % 64;
        }
        if (sub >= 16) {
            count += sub / 16;
            sub = sub % 16;
        }
        if (sub >= 4) {
            count = sub % 4;
        }
        count += sub;
        return count;
    }

    @Test
    public void test3() {
        System.out.println(GetCoinCount(200));
    }
}
