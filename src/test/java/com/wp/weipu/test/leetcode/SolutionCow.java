package com.wp.weipu.test.leetcode;

import org.junit.Test;

public class SolutionCow {
    public int solution(int n, int k, int[] a) {
        int index = divide(k, a);
        sort(0, index - 1, a);
        sort(index, a.length - 1, a);
        int i = 0, j = a.length - 1;
        if (a[index] > k) {
            i = index;
        } else {
            i = index - 1;
        }
        int index2 = i;
        int time = 0;
        while (i >= 0 && j >= index2) {
            int num1 = k - a[j];
            int num2 = a[i] - k;
            if (num2 == num1) {
                time += num1;
                i--;
                j--;
            } else if (num2 < num1) {
                time += num2;
                i--;
                a[j] += num2;
            } else {
                int sub = num2 - num1;
                time += sub;
                j--;
                a[i] -= sub;
            }
        }

        return time;
    }

    public void sort(int start, int end, int[] a) {
        /**
         * 倒叙
         */
        if (start > end) {
            return;
        }
        int point = a[start];
        int i = start, j = end;
        int tmp;
        while (i < j) {
            while (i < j && a[j] <= point) {
                j--;
            }
            while (i < j && a[i] >= point) {
                i++;
            }
            if (i < j) {
                tmp = a[j];
                a[j] = a[i];
                a[i] = tmp;
            }
        }
        tmp = a[start];
        a[start] = a[i];
        a[i] = tmp;
        sort(start, i - 1, a);
        sort(i + 1, end, a);
    }

    public int divide(int k, int[] a) {
        //排序一些
        int len = a.length;
        int left = 0, right = len - 1;
        int tmp;
        while (left < right) {
            while (left < right && a[right] < k) {
                right--;
            }

            while (left < right && a[left] > k) {
                left++;
            }
            if (left < right && a[right] > k && a[left] < k) {
                tmp = a[right];
                a[right] = a[left];
                a[left] = tmp;
                left++;
                right--;
            } else {
                break;
            }
        }
        return left;
    }

    @Test
    public void main() {
        int[] a = {1, 2, 4, 2, 8, 6};
        sort(0, a.length - 1, a);
        for (int i : a) {
            System.out.print(i);
        }
//        System.out.println("inde:" + index);
    }

    public int maxSubArray(int[] nums) {
        int sum = 0;
        int res = nums[0];
        for (int i : nums) {
            if (sum > 0) {
                sum += i;
            } else {
                sum = i;
            }
            res = Math.max(sum, res);
        }
        return res;
    }

    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            max = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);
            max = Math.max(max, imax);
        }
        return max;
    }


}
