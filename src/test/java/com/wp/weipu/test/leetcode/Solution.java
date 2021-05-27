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

    public Map<String, String> getDigitMap() {
        Map<String, String> digitsMap = new HashMap<>();
        digitsMap.put("2", "abc");
        digitsMap.put("3", "def");
        digitsMap.put("4", "ghi");
        digitsMap.put("5", "jkl");
        digitsMap.put("6", "mno");
        digitsMap.put("7", "pqrs");
        digitsMap.put("8", "tuv");
        digitsMap.put("9", "wxyz");
        return digitsMap;
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        Map<String, String> digitsMap = getDigitMap();
        getDigit("", result, digits, digitsMap);
        return result;
    }

    /**
     * @param dig    这个是上一循环组合的
     * @param digits 这个是集合
     * @param digit  这个是剩下的字符
     */
    public void getDigit(String dig, List<String> digits, String digit, Map<String, String> digitMap) {
        if ("".equals(digit)) {
            if (!"".equals(dig)) {
                digits.add(dig);
            }
            return;
        }
        int len = digit.length() - 1;
        int index = 0;
        char key = digit.charAt(index);
        while ('1' == key) {
            index++;
            if (index <= len) {
                key = digit.charAt(index);
            } else {
                if (!"".equals(dig)) {
                    digits.add(dig);
                }
                return;
            }
        }
        String di = digitMap.get(key + "");
        for (char i : di.toCharArray()) {
            getDigit(dig + i, digits, digit.substring(index + 1, len + 1), digitMap);
        }
    }


    @Test
    public void testDig() {
        List<String> res = letterCombinations("13231");
        System.out.println(res.toString());
    }

    /***
     * 四个数之和，无重复,滑动窗口不行，没思路
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
//        List<Integer>
        return null;
    }

    public void quickSort2(int[] nums, int i, int j) {
        if (j <= i) {
            return;
        }
        int start = i, end = j;
        int tag = nums[start];
        while (start < end) {
            while (nums[end] > tag && end > start) {
                end--;
            }
            nums[start] = nums[end];
            while (nums[start] < tag && end > start) {
                start++;
            }
            nums[end] = nums[start];
        }
        nums[start] = tag;
        quickSort(nums, i, start - 1);
        quickSort(nums, end + 1, j);
    }

    //todo:过几天再实现
    public void stack(int[] nums, int n) {
        // 生成初始堆
        for (int i = n / 2; i > 1; i--) {
            shift(nums, i, n);
        }
        int tmp;
        // 调整
        for (int i = n; i >= 2; i--) {
            tmp = nums[1];
            nums[1] = tmp;
            nums[i] = tmp;
            shift(nums, 1, i - 1);
        }
    }

    /**
     * LCP 33. 蓄水
     *
     * @param bucket
     * @param vat
     * @return
     */
    public int storeWater(int[] bucket, int[] vat) {
        int max = 0;
        for (int v : vat) {
            if (max < v) {
                max = v;
            }
        }
        if (max == 0) {
            return 0;
        }
        int n = bucket.length;
        int ans = Integer.MAX_VALUE;
        //遍历倒水次数
        for (int i = 1; i <= 10000; i++) {
            int per = 0;
            int cur = i;
            for (int j = 0; j < n; j++) {
                per = (vat[j] + i - 1);
                cur += Math.max(0, per - bucket[j]);
            }
            ans = Math.min(ans, cur);
        }
        return ans;
    }

    public void shift(int[] nums, int i, int j) {
        int low = i, cur = 2 * i;
        int tmp = nums[i];
        while (cur < j) {
            if (cur < j && nums[cur] < nums[cur + 1]) {
                cur++;
            }
            if (cur < j && nums[cur] > tmp) {
                nums[low] = nums[cur];
                low = cur;
                cur = 2 * low;
            } else {
                break;
            }
        }
        nums[low] = tmp;
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

