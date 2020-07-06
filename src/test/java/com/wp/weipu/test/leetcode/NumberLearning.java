package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 数字运算
 */
public class NumberLearning {
    /**
     * 判断n是否为某数的二次方，不能使用求根公式
     * 使用二分查找
     *
     * @param n
     */
    public boolean isPower(int n) {
        int low = 0, high = n;
        while (low < high) {
            int mid = (high + low) / 2;
            if (mid * mid < n) {
                low = mid + 1;
            } else if (mid * mid == n) {
                return true;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    /**
     * 判断是不是2n次方
     *
     * @param n
     * @return
     */
    public boolean isPower2(int n) {
        int i = 1;
        while (i <= n) {
            if (i == n) {
                return true;
            }
            i = i << 1;
        }
        return false;
    }

    public boolean isPower3(int n) {
        return (n & (n - 1)) == 0;
    }

    /**
     * 不用除非运算符做除法运算
     * 第一个是用减法，，，
     * 第二种用位运算
     *
     * @return
     */
    public int divide(int m, int n) {
        int i, result = 0;
        //只有大于等于才能做除
        while (m >= n) {
            i = 1;
            while (i * n <= (m >> 1)) {
                i <<= 1;
            }
            result += i;
            m -= i * n;
        }
        return result;
    }

    /**
     * 使用位运算做加法
     *
     * @param m
     * @param n
     */
    public int sum(int m, int n) {
        int a = 1, b = 1;
        while (b != 0) {
            // 计算各个位置相加
            a = m ^ n;
            //计算进位相加
            b = m & n;
            b <<= 1;
            m = a;
            n = b;
        }
        System.out.println(a);
        return a;
    }

    @Test
    public void testSum() {
//        sum(1, 5);
        System.out.println(44 >> 4);
    }

    /**
     * 乘法运算
     *
     * @param a
     * @param b
     * @return
     */
    public int multi(int a, int b) {
        //结果的正负标识符
        boolean neg = (a > 0) ^ (b > 0);
        //首先计算两个正数相乘的结果，最后根据neg确定结果的正负
        if (b < 0) {
            // 取补码=反码+1，就是取负数
            b = sum(~b, 1);
        }
        if (a < 0) {
            a = sum(~a, 1);
        }
        Map<Integer, Integer> bitPosition = new HashMap<>();
        for (int i = 0; i < 32; i++) {
            //每个只存在一个1的数需要左移多少位
            bitPosition.put((1 << i), i);
        }

        int sum = 0;
        while (b > 0) {
            //计算出最后一个1的位置
            int position = bitPosition.get(b & ~(b - 1));
            sum += (a << position);
            //去掉最后一位1
            b &= b - 1;
        }
        if (neg) {
            sum = sum(~sum, 1);
        }
        return sum;
    }

    /**
     * 查找最小的无重复
     *
     * @param num
     * @return
     */
    public int finMinNoDupNum(int num) {
        String numStr = String.valueOf(num);
        char[] nums = numStr.toCharArray();
        //用来处理进位的
        char[] nums2 = new char[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            nums2[i + 1] = nums[i];
        }
        int len = nums2.length;
        int j = 2;
        while (j < len) {
            if (nums2[j - 1] == nums2[j]) {
                nums2[j]++;
                carry(nums2, j);
                //把后面的变成0101的
                for (int m = j + 1; m < len; m++) {
                    if ((m - j) % 2 == 0) {
                        nums2[m] = '1';
                    } else {
                        nums2[m] = '0';
                    }
                }
            } else {
                j++;
            }
        }
        return Integer.parseInt(String.valueOf(nums2));
    }

    /**
     * 处理进位
     */
    public void carry(char[] ch, int i) {
        for (int j = i; j > 0; j--) {
            if (ch[j] > '9') {
                ch[j] = '0';
                ch[j - 1]++;
            }
        }
    }

    /**
     * 使用递归的方法
     * 我这里只考虑了n>0的情况
     *
     * @return
     */
    public int doublePower(int power, int n) {
        if (n == 1) {
            return power;
        }
        if (n % 2 == 1) {
            return doublePower(power, n / 2) * power * doublePower(power, n / 2);
        } else {
            return doublePower(power, n / 2) * doublePower(power, n / 2);
        }
    }

    /**
     * 双指针，用来求最短距离
     *
     * @param s
     * @param nums
     * @return
     */
    public int maxMinLen(int s, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int start = 0, end = 0, len = nums.length;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        while (end < len) {
            sum += nums[end];
            while (sum >= s) {
                minLen = Math.min(minLen, end - start);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;

    }
}
