package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head, headTemp = new ListNode(0);
        head = headTemp;
        int ll1 = 0, ll2 = 0, yu = 0, num;
        while (l1 != null || l2 != null || yu != 0) {
            if (l1 != null) {
                ll1 = l1.val;
                l1 = l1.next;
            } else {
                ll1 = 0;
            }
            if (l2 != null) {
                ll2 = l2.val;
                l2 = l2.next;
            } else {
                ll2 = 0;
            }
            num = ll1 + ll2 + yu;
            if (num >= 10) {
                num = num % 10;
                yu = 1;
            } else {
                yu = 0;
            }
            ListNode newl = new ListNode(num);
            headTemp.next = newl;
            headTemp = headTemp.next;
        }
        return head;
    }

    @Test
    public void main() {
        int[] il1 = {2, 4, 3};
        int[] il2 = {5, 6, 4, 6};
        ListNode hl1 = new ListNode(0), hl2 = new ListNode(0), l1, l2;
        l1 = hl1;
        l2 = hl2;
        for (int i : il1) {
            hl1.next = new ListNode(i);
            hl1 = hl1.next;
        }
        for (int i : il2) {
            hl2.next = new ListNode(i);
            hl2 = hl2.next;
        }
        System.out.println(l1);
        System.out.println(l2);
        ListNode l = addTwoNumbers(l1.next, l2.next);
        System.out.println(l);
//        System.out.println(hl2);


//        System.out.println(head);
    }

    /**
     * Z字排列数据
     *
     * @param s
     * @param numRows
     * @return
     */

    public String convert(String s, int numRows) {
        double strLen = s.length();
        if (numRows == 1) {
            return s;
        }
        int numCols = (int) (Math.ceil(strLen / (2 * numRows - 2)) * (numRows - 1));
        char[][] mat = new char[numRows][numCols];
        char[] parse = s.toCharArray();
        int parseIndex = 0, parseSize = parse.length;
        int row = 0, col = 0;
        while (col < numCols) {
            while (row < numRows) {
                mat[row][col] = parse[parseIndex];
                if (parseIndex == parseSize - 1) {
                    return matToString(mat);
                }
                parseIndex++;
                row++;
            }
            row = row - 2;
            col++;
            while (row >= 0) {
                mat[row][col] = parse[parseIndex];
                if (parseIndex == parseSize - 1) {
                    return matToString(mat);
                }
                parseIndex++;
                row--;
                col++;
            }
            col--;
            row = row + 2;
        }
        return matToString(mat);
    }

    public String matToString(char[][] mat) {
        String result = "";
        for (char[] row : mat) {
            for (char a : row) {
                if (a == '\0') {
                    continue;
                }
                result = result + a;
            }
        }
        return result;
    }

    @Test
    public void convertTest() {
        String s = "AB";
        String res = convert(s, 1);
        System.out.println(res);
        char[] a = new char[2];
        System.out.println("here");
        if (String.valueOf(a[1]).equals(" ")) {
            System.out.println("ok");
        }
        System.out.println(String.valueOf(a[0]));
    }

    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        boolean firstMatch = (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*') {
            //这两个公式是什么意义。。
            return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }

    }

    @Test
    public void isMatch() {

    }


    boolean[][] memo;

//    public boolean isMatch1(String s, String p) {
//        memo = new boolean[s.length()][p.length()];
//
//    }

    public boolean isMatch2(String s, String p) {
        boolean[][] memo = new boolean[s.length() + 1][p.length() + 1];
        //当s,p长度为零时
        memo[0][0] = true;
        //当模式串为空的时候
        for (int i = 1; i < s.length() + 1; i++) {
            memo[i][0] = false;
        }
        for (int j = 1; j < p.length() + 1; j++) {
            for (int i = 1; i < s.length() + 1; i++) {
//                if (memo[i][j]!=null)
            }
        }
        return false;

    }

    //烂苹果游戏
    public int orangesRotting(int[][] grid) {
        int minute = 0;
        int row = grid.length, col = grid[1].length;
        //按行遍历
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boolean flag = false;
                if (grid[i][j] == 2) {
                    if (i + 1 < row && grid[i + 1][j] == 1) {
                        grid[i + 1][j] = 2;
                        flag = true;
                    }
                    if (j + 1 < col && grid[i][j + 1] == 1) {
                        grid[i][j + 1] = 2;
                        flag = true;
                    }
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        grid[i - 1][j] = 2;
                        flag = true;
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        grid[i][j - 1] = 2;
                        flag = true;
                    }
                }
                if (flag) {
                    minute++;
                }
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return minute;
    }

    //怎么不加重复，怎么计数,还有2可能出现在任意位置。。
    public int orangesRottingIter(int[][] grid, int i, int j) {

        return 0;
    }

    @Test
    public void organ() {
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int minute = orangesRotting(grid);
        System.out.println(minute);
    }

    /***
     * 数组中三个数之和最接近目标数
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        int end = nums.length - 1;
        quickSort(nums, 0, end);
        int sum = 0, min = 1000;
        for (int i = 0; i < nums.length - 1; i++) {
            end = nums.length - 1;
            int mid = i + 1;
            while (mid < end) {
                int s = nums[i] + nums[mid] + nums[end];
                int tempMin = s - target;
                if (Math.abs(tempMin) < min) {
                    sum = nums[i] + nums[mid] + nums[end];
                    min = Math.abs(tempMin);
                }
                if (s > target) {
                    end--;
                }
                if (s < target) {
                    mid++;
                }
                if (s == target) {
                    return s;
                }
            }
        }
        return sum;
    }

    public void quickSort(int[] num, int low, int height) {
        if (low >= height) {
            return;
        }
        int i = low, j = height;
        //基准
        int temp = num[low];
        while (i < j) {
            while (num[j] > temp && j > i) {
                j--;
            }
            num[i] = num[j];
            while (num[i] <= temp && i < j) {
                i++;
            }
            num[j] = num[i];
        }
        num[i] = temp;
        quickSort(num, low, i - 1);
        quickSort(num, j + 1, height);
    }

    @Test
    public void sameTeat() {
        int[] num = {1, 2, 4, 8, 16, 32, 64, 128};
        int res = threeSumClosest(num, 82);
        System.out.println(res);
    }

    public List<String> letterCombinations(String digits) {
        Map<String, ArrayList> digitsMap = new HashMap<>();
        return null;
    }
}


//  Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }


}

