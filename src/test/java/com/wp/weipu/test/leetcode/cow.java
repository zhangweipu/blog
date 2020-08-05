package com.wp.weipu.test.leetcode;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class cow {

    /**
     * 路径问题
     */
    public void test1() {

    }

    /**
     * 搜索最小值 a,b之间的专线长度是不知道的
     *
     * @param n
     * @param nums
     * @param row
     */
    public int getT(int n, int[][] nums, int row[]) {
        //low是最短距离
        int low = -1, upper = row[2];
        int a = row[0], b = row[1], k = row[2];
        while (upper - low > 1) {
            //二分查找的方式,代表查找的值
            int mid = (upper + low) / 2;
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int d = nums[i][j];
                    //算是挨个中转的试
                    d = Math.min(d, nums[i][a] + nums[b][i] + mid);
                    d = Math.min(d, nums[i][b] + nums[a][i] + mid);
                    if (d + mid >= k) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
            }
            if (!flag) {
                upper = mid;
            } else {
                low = mid;
            }
        }
        return low;
    }

    /**
     * h是红绿灯的高，nums是排队的车辆
     * 肯定是通过计算比例
     * 前一个的斜率小能当住，斜率大挡不住
     *
     * @param h
     * @param nums
     * @return
     */
    public List<Integer> getHeight(int h, int[] nums) {
        int size = nums.length;
        List<Integer> list = new ArrayList<>(size);
        for (int i = 1; i < size; i++) {
            int j = i;
            //第一辆车肯定是红绿灯啊
            if (j == 1) {
                list.add(j - 1);
                continue;
            }
            int curCar = nums[i];
            while (j > 0) {
                int preCar = nums[j];
                int sub = preCar - curCar;
                if (sub <= 0) {
                    j--;
                } else {
                    double rate = (i - j) / i * 1.0;
                    double rete2 = sub / (h - nums[i]) * 1.0;
                    if (rete2 > rate) {
                        list.add(j);
                        break;
                    }
                    j--;
                }
            }
            //如果没有当住的
            if (list.size() < i) {
                list.add(0);
            }
        }
        return list;
    }

    /**
     * 数组元素交换k次，生成最大值
     * 找到最大的小于k的数往前交换
     * 知道k用完
     *
     * @param nums
     * @param k
     */
    public int[] exchangeK(int[] nums, int k) {
        int len = nums.length;
        //先求最大值
        int index = 0;
        for (int i = 0; i < len; i++) {
            //必须保证k大于等于0才行
            if (k > 0) {
                index = getMaxIndex(nums, i, k);
            } else {
                break;
            }
            int tmpIndex = index;
            while (tmpIndex > i) {
                int tmp = nums[tmpIndex - 1];
                nums[tmpIndex - 1] = nums[tmpIndex];
                nums[tmpIndex] = tmp;
                tmpIndex--;
                k--;
            }
        }
        return nums;
    }

    public int getMaxIndex(int[] nums, int start, int k) {
        int max = 0, index = 0;
        int len = nums.length;
        for (int i = start; i < len; i++) {
            if (k == -1) {
                break;
            }
            if (max < nums[i]) {
                max = nums[i];
                index = i;
            }
            k--;
        }
        return index;
    }

    @Test
    public void test3() {
        int[] nums = new int[]{4,2,1,3,5};
        int[] res = exchangeK(nums, 5);
        System.out.println(JSON.toJSONString(res));
    }
}
