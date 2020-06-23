package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 数组的相关练习
 */
public class ArrayLearn {

    public int findDup(int[] array) {
        if (null == array) {
            return -1;
        }
        int result = 0;
        for (int arr : array) {
            result ^= arr;
        }
        for (int i = 1; i < array.length; i++) {
            result ^= i;
        }
        return result;
    }

    /**
     * 分治法，将数组两两一对分组，如果数组为奇数个，
     * 就把最后一个元素单独分为一组
     *
     * @param arr
     */
    public void maxAndMin(int[] arr) {
        if (arr == null) {
            return;
        }
        int max = arr[0];
        int min = arr[0];
        int len = arr.length;
        //两两分组，较小的放左边，较大的放右边
        for (int i = 0; i < len - 1; i += 2) {
            if (arr[i] > arr[i + 1]) {
                int tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
            }
        }
        min = arr[0];
        //分组的左边找到最小值
        for (int i = 2; i < len - 1; i += 2) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        max = arr[1];
        for (int i = 3; i < len - 1; i += 2) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        if (len % 2 == 1) {
            if (max < arr[len - 1]) {
                max = arr[len - 1];
            }
            if (min > arr[len - 1]) {
                min = arr[len - 1];
            }
        }
        System.out.println(min);
        System.out.println(max);
    }

    public List<Integer> maxAndMin(int[] arr, int i, int j) {
        if (arr == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        // 返回结果
        if (i == j) {
            res.add(arr[i]);
            res.add(arr[i]);
            return res;
        }
        //有两个的情况
        if (i + 1 == j) {
            if (arr[i] > arr[j]) {
                res.add(arr[j]);
                res.add(arr[i]);
            } else {
                res.add(arr[i]);
                res.add(arr[j]);
            }
            return res;
        }
        //java是向下取整
        int mid = (i + j) / 2;
        List<Integer> lres = maxAndMin(arr, i, mid);
        List<Integer> rRes = maxAndMin(arr, mid + 1, j);
        int max = lres.get(1) > rRes.get(1) ? lres.get(1) : rRes.get(1);
        int min = lres.get(0) < rRes.get(0) ? lres.get(0) : rRes.get(0);
        res.add(min);
        res.add(max);
        return res;
    }

    /**
     * 拆成1-25的，怎么判断几位数
     * 取余数这不太好，转成字符串吗
     * 把数字翻译成字符串
     *
     * @param num
     */
    public void splitNum(int num) {

    }

    public void splitNumStr(String num) {
        int len = num.length();
        for (int i = 1; i < len - 1; i++) {

        }
    }

    /**
     * 最大连续字串和，可以使用暴力法，
     * 也可以使用保存状态的数组。。。
     *
     * @param arr
     */
    public int maxSubArray(int[] arr) {
        if (arr.length == 0) {
            return Integer.MIN_VALUE;
        }
        int n = arr.length;
        // 初始化数组保存，前面相加的状态
        int[] end = new int[n];
        // 保存最大值
        int[] all = new int[n];
        //我也不知道目的
        end[n - 1] = arr[n - 1];
        all[n - 1] = arr[n - 1];
        end[0] = arr[0];
        all[0] = arr[0];
        for (int i = 1; i < n; ++i) {
            // 这一句是关键，可以改成使用滚动数组，实现
            end[i] = Math.max(end[i - 1] + arr[i], arr[i]);
            all[i] = Math.max(end[i], all[i - 1]);
        }
        return all[n - 1];
    }

    /**
     * 滚动数组的做法。。。。。
     *
     * @param arr
     * @return
     */
    public int maxSubArray1(int[] arr) {
        if (arr.length == 0) {
            return Integer.MIN_VALUE;
        }
        // 滚动数组
        int end = arr[0];
        int all = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            end = Math.max(end + arr[i], arr[i]);
            all = Math.max(end, all);
        }
        return all;
    }

    /**
     * 判断数字n的二进制数从右往左数第i位是否为1
     * 移位啥的最难了。。。
     *
     * @param n
     * @param i
     * @return
     */
    public boolean isOne(int n, int i) {
        return ((n & (1 << i)) == 1);
    }

    /**
     * 返回出现次数为一次的任意一个
     *
     * @param arr
     * @return
     */
    public int findSingle(int[] arr) {
        System.out.println((32 & (1 << 5)));
        return 0;
    }

    public void rotateArr(int[][] arr) {
        // 待用。。。。
        int row, col;
        int len = arr.length;
        //从列开始计数
        for (int i = len - 1; i > 0; i--) {
            row = 0;
            col = i;
            while (row < len) {
                //输出上三角形
                System.out.println(arr[row++][col++] + ",");
            }
        }
        //从行开始
        for (int i = 0; i < len; i++) {
            col = 0;
            row = i;
            while (row < len) {
                System.out.println(arr[row++][col++] + ",");
            }
        }

    }

    /**
     * 找到和目标值最接近的数
     *
     * @param arr
     * @param target
     * @return
     */
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            //求和，计算累积的值
            prefix[i] = prefix[i - 1] + prefix[i - 1];
        }
        //枚举的范围
        int r = arr[n - 1];
        int ans = 0, diff = target;
        for (int i = 1; i <= r; i++) {
            //通过二分查找，查索引
            int index = Arrays.binarySearch(arr, i);
            if (index < 0) {
                //这个我有点不明白啊，获得的是最接近值的索引吗？？？
                index = -index - 1;
            }
            int cur = prefix[index] + (n - index) * i;
            //记录最小值，，和我的想法一样
            if (Math.abs(target - cur) < diff) {
                ans = i;
                diff = Math.abs(cur - target);
            }

        }
        return ans;
    }

    /**
     * 获取两个不重复的数字
     * 使用位运算法
     *
     * @param arr
     */
    public void get2num(int[] arr) {
        //不判断数组的格式了
        int result = 0;
        int position = 0;
        for (int i = 0; i < arr.length; i++) {
            //做异或运算
            result = result ^ arr[i];
        }
        int tmpResult = result;
        //查找某个数有1的位置和result一样
        for (int i = result; ((i & 1) == 0); i = i >> 1) {
            position++;
        }
        for (int i = 0; i < arr.length; i++) {
            //如果用重复的还是给异或掉了。。。
            if (((arr[i] >> position) & 1) == 1) {
                result = result ^ arr[i];
            }
        }
        System.out.println(result);
        System.out.println(result ^ tmpResult);
    }

    /**
     * 在无序数组中找到中位数
     *
     * @param arr
     */
    public void getMidNum(int[] arr) {
        int len = arr.length;
        //因为要递归，所以需要使用这种形式
        int high = len - 1, low = 0;
        int mid = (high - low) / 2;
        //这个只需要处理数据的一半就行
        while (true) {
            int pos = part(arr, high, low);
            if (pos == mid) {
                break;
            } else if (pos < mid) {
                high = pos - 1;
            } else {
                low = pos + 1;
            }
        }
        //判断个数是否为偶数个。。。。这也算排序了啊。。
    }

    /**
     * 这里是进行划分的，但是有点像快排
     */
    public int part(int[] arr, int high, int low) {
        //基准
        int key = arr[low];
        while (low < high) {
            while (low < high && arr[high] > key) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] < key) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = key;
        //基准的位置
        return low;
    }

    /**
     * 求全部子集
     * 使用迭代
     */
    public void getAllSubset(String str) {
        List<String> list = new ArrayList<>();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            int size = list.size();
            for (int j = 0; j < size; j++) {
                list.add(list.get(j) + str.charAt(i));
            }
            list.add(str.substring(i, i + 1));
        }
    }

    /**
     * 通过二进制移位
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subset(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < 1 << nums.length; i++) {
            List<Integer> path = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                //判断j位置上的二进制位是否为1
                if (((i >> j) & 1) == 1) {
                    path.add(nums[j]);
                }
            }
            ans.add(path);
        }
        return ans;
    }

    /**
     * 所有元素向右移动k位
     *
     * @param arr
     * @param k
     */
    public void rightShift(int[] arr, int k) {
        int len = arr.length;
        for (int i = 0; i < k; i++) {
            int head = arr[0];
            for (int j = 1; j < len; j++) {
                int tmp = arr[j];
                arr[j] = head;
                head = tmp;
            }
            arr[0] = head;
        }
    }

    /**
     * 在有序的二维数组中查找目标值
     *
     * @param arr
     * @param key
     * @return
     */
    public boolean findWithBinary(int[][] arr, int key) {
        //column是列 rows是行
        int col = arr[0].length, row = arr.length;
        int j = 0;
        for (int i = col - 1; i >= 0; i--) {
            if (j < row && arr[j][i] == key) {
                return true;
            } else if (j < row && arr[j][i] < key) {
                j++;
                //这个地方，不能继续往前了，所以要加
                i++;
            }
        }
        return false;
    }

    /**
     * 一个整数可以拆分成和的方式可以有多少种
     * 有个人是通过set去重这种方式不好
     * 怎么记忆化。。。
     *
     * @param num
     * @return
     */

    public int splitNumRecursion(int num) {
        int total = 0;
        if (num == 1 || num == 2 || num == 0) {
            return 1;
        }
        // 有重复的，怎么去重。。。。
        for (int i = num - 1; i > 0; i--) {
            total += splitNumRecursion(num - i);
        }
        return total;
    }

    public int q(int n, int m) {
        if (n == 1 || m == 1) {
            return 1;
        }
        if (n < m) {
            return q(n, n);
        }
        if (n == m) {
            return q(n, m - 1) + 1;
        }

        return q(n, m - 1) + q(n - m, m);
    }

    @Test
    public void splitNum2() {
        System.out.println(q(6, 6));
    }

    public void bubbleSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = len - 1; j > i; j--) {
                if (arr[j] < arr[i]) {
                    int tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }
    }

    @Test
    public void test() {
//        System.out.println((4 ^ 2 ^ 3) ^ (3 ^ 2 ^ 1));
        int[] arr = {1, 2, 3, 4, 5, 8, 7};
//        maxAndMin(arr);
//        System.out.println(2 ^ 3);
//        System.out.println(maxSubArray(arr));
//        findSingle(arr);
        int[][] arrM = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(0 % 2 == 0);
        int[] arr2 = {3, 5, 7, 6, 5, 11, 2, 2};
        bubbleSort(arr2);
        for (int a : arr2) {
            System.out.println(a);
        }
        //这个判断没用的吧
//        System.out.println(4 >> 1);
        int[][] arr3 = {{1, 2, 8, 9, 11},
                {2, 4, 9, 12, 14},
                {4, 7, 10, 13, 15},
                {6, 8, 11, 15, 16}};
        System.out.println(findWithBinary(arr3, 13));
    }

    public int fib(int n) {
        int[] res = new int[n];
        res[0] = 1;
        res[1] = 1;
        for (int i = 2; i < n; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
        for (int i = 0; i < n; i++) {
            System.out.println(res[i]);
        }
        return 0;
    }

    public int fibRecursion(int n, int[] res) {
        if (n == 1 || n == 2) {
            res[n] = 1;
            return 1;
        }
        res[n] = fibRecursion(n - 1, res) + fibRecursion(n - 2, res);
        return res[n];
    }

    @Test
    public void test2() {
        int n = 5;
//        fib(10);
        String aa = "231";
        int b = aa.indexOf("1");
        System.out.println(b);
        List<String> list = new ArrayList<>();
        list.add("231");
        list.forEach(System.out::println);

    }
}
