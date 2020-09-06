package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.lang.reflect.Array;
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

    /**
     * 链表两两进行角环
     * 不带头节点的
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null | head.next == null) {
            return head;
        }
        ListNode first, second = head.next;
        first = second.next;
        second.next = swapPairs(first);
        return second;
    }

    @Test
    public void test3() {

        System.out.println((int) Math.ceil(1 / 2.0));
        int[][] m = {{1}};
        System.out.println(m.length);
        System.out.println(m[0].length);
    }

    /**
     * 数组只能向右向下,正向走不对，我想的太简单了
     *
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP1(int[][] dungeon) {
        //row是行，col是列
        int col = dungeon[0].length, row = dungeon.length;
        if (col == 1 && row == 1) {
            return dungeon[0][0] > 0 ? 1 : Math.abs(dungeon[0][0]) + 1;
        }
        if (col == 1) {

        }
        int[][] memory = new int[row][col];
        memory[0][0] = dungeon[0][0];
        //填充第一列
        for (int i = 1; i < row; i++) {
            memory[i][0] = memory[i - 1][0] + dungeon[i][0];
        }
        for (int j = 1; j < col; j++) {
            memory[0][j] = memory[0][j - 1] + dungeon[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                memory[i][j] = Math.min(memory[i - 1][j], memory[i][j - 1]);
            }
        }
        return memory[col - 1][row - 1];
    }

    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length, m = dungeon[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[n][m - 1] = dp[n - 1][m] = 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                int minn = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(minn - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }

    /**
     * 求大于这个数的下一个数
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] < nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect1(nums2, nums1);
        } else {
            return intersect1(nums1, nums2);
        }
    }

    public int[] intersect1(int[] num1, int[] num2) {
        Map<Integer, Integer> set = new HashMap<>();
        for (int n : num1) {
            set.merge(n, 1, Integer::sum);
        }
        int[] num = new int[num1.length];
        int j = 0;
        for (int i = 0; i < num2.length; i++) {
            if (set.containsKey(num2[i]) && set.get(num2[i]) > 0) {
                num[j] = num2[i];
                j++;
                set.merge(num2[i], -1, Integer::sum);
            }
        }
        return Arrays.copyOfRange(num, 0, j);
    }

    @Test
    public void test4() {
        int[] n = new int[]{1, 2, 2, 1};
        int[] m = new int[]{2, 2};
        for (int j : intersect(n, m)) {
            System.out.println(j);
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        return null;
    }

    public ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode node = new ListNode(-1), tmp;
        while (head != null) {
            tmp = node.next;
            node.next = head;
            head = head.next;
            node.next.next = tmp;
        }
        return node.next;
    }

    public ListNode reverse1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tmp = head, tmp2;
        head = head.next;
        tmp.next = null;

        while (head != null) {
            tmp2 = head.next;
            head.next = tmp;
            tmp = head;
            head = tmp2;
        }
        return tmp;
    }

    @Test
    public void test6() {
        ListNode node = new ListNode(1), head;
        head = node;
        node.next = new ListNode(2);
        node = node.next;
        node.next = new ListNode(99);
        node = node.next;
        node.next = new ListNode(3);

        ListNode root = reverse1(head);
        while (root != null) {
            System.out.println(root.val);
            root = root.next;
        }

    }

    /**
     * 三角形的最短路径
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int level = triangle.size();
        int[] min = new int[level];
        for (int i = 0; i < level; i++) {
            List<Integer> subTriangle = triangle.get(i);
        }
        return 0;
    }

    /**
     * A数组的长度，等于两个数组的和
     * 采用插入排序的方式
     * <p>
     * 有一个大佬的思路是从后往前插入，就是计算索引有点复杂
     *
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int[] A, int m, int[] B, int n) {
        int j = 0;
        for (int i = 0; i < n; i++) {
            //找位置
            while (j < m) {
                //这一步的时候会和0比较，容易出问题
                if (B[i] > A[j] && A[j] != 0) {
                    j++;
                } else {
                    break;
                }
            }
            insert(A, j, B[i]);
        }

    }

    public void insert(int[] nums, int i, int num) {
        assert nums.length > i : "插入点大于数组长度";
        int tmp = nums[i];
        for (int j = i + 1; j < nums.length; j++) {
            int tmp2 = nums[j];
            nums[j] = tmp;
            tmp = tmp2;
        }
        nums[i] = num;
    }

    @Test
    public void test7() {
        int[] num1 = {1, 2, 3, 4, 5, 0, 0, 0, 0, 0};
        int[] num2 = {2, 3, 4, 5, 6};
        merge(num1, 10, num2, 5);
        for (int i : num1) {
            System.out.println(i);
        }
    }

    /**
     * 判断是不是二分图，二分图就是把节点可以分成两块，两块的节点间没有边
     * 可以采用DFS
     * 给节点染色的方式
     *
     * @param 
     * @return
     */
    class Solution {
        //节点颜色
        private static final int UNCOLORED = 0;
        private static final int RED = 1;
        private static final int GREEN = 2;
        //声明存颜色的数组，根据节点来的，我也可以用hashmap
        private int[] color;
        private boolean valid;

        public boolean isBipartite(int[][] graph) {
            //节点的值，不大于节点的个数
            int n = graph.length;
            valid = true;
            color = new int[n];
            //使用了数组方法
            Arrays.fill(color, UNCOLORED);
            for (int i = 0; i < n; i++) {
                dfs(graph, i, RED);
            }
            //是否是二分图
            return valid;
        }

        /**
         * 采用深度遍历的方式，遍历整个图
         */
        public void dfs(int[][] graph, int node, int c) {
            color[node] = c;
            //其邻居节点的颜色
            int nextColor = c == RED ? GREEN : RED;
            for (int i : graph[node]) {
                if (color[i] == UNCOLORED) {
                    dfs(graph, i, nextColor);
                    //这个是在已经判定结束了，不再继续执行
                    if (!valid) {
                        return;
                    }
                } else if (color[i] == c) {
                    valid = false;
                    return;
                }
            }
        }
    }

    /**
     * 怎么判断溢出
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if(dividend==Integer.MIN_VALUE&&divisor==-1){
            return Integer.MAX_VALUE;
        }
        if(divisor==1){
            return dividend;
        }
        int res=0;
        boolean neg=(dividend<0&&divisor>0)||(divisor<0&&dividend>0);
        //转成负数可以防止数组过界 -2**31 2**31-1
        divisor=-Math.abs(divisor);
        dividend=-Math.abs(dividend);

        while(dividend<=divisor){
            dividend-=divisor;
            res++;
        }
        return neg?-res:res;
    }

    @Test
    public void test8() {
        int divs = -2147483648;
        int div = -1;
        System.out.println(-Math.abs(divs));
    }

    /**
     * 四数之和，不能重复
     * 先固定四个数的前两个值，前两个数肯定是小的
     * 然后通过双指针去求后两个数
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if (len < 4) {
            //数组长度不够，返回空
            return result;
        }
        Arrays.sort(nums);
        //这样表示数组中没有符合的四个数
        int minvalue = nums[0] + nums[1] + nums[2] + nums[3];
        int maxValue = nums[len - 1] + nums[len - 2] + nums[len - 3] + nums[len - 4];
        if (minvalue > target || maxValue < target) {
            return result;
        }
        for (int one = 0; one <= len - 4; one++) {
            //固定第一个数
            int oneValue = nums[one];
            if (one > 0 && nums[one] == nums[one - 1]) {
                continue;
            }
            for (int two = one + 1; two <= len - 3; two++) {
                //固定第二个数
                int twoValue = nums[two];
                int left = two + 1, right = len - 1;
                if (two > one + 1 && nums[two] == nums[two - 1]) {
                    continue;
                }
                minvalue = oneValue + twoValue + nums[two + 1] + nums[two + 2];
                maxValue = oneValue + twoValue + nums[len - 1] + nums[len - 2];
                //这个时候还不能返回
                if (maxValue < target || minvalue > target) {
                    left = right;
                }
                //先确定前两个，再通过双指针做
                while (left < right) {
                    int fourNumSum = oneValue + twoValue + nums[left] + nums[right];
                    if (fourNumSum > target) {
                        right--;
                        while (right > left && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (fourNumSum < target) {
                        left++;
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                    } else {
                        List<Integer> res = new ArrayList<>();
                        res.add(oneValue);
                        res.add(twoValue);
                        res.add(nums[left]);
                        res.add(nums[right]);
                        result.add(res);
                        left++;
                        right--;
                        while (right > left && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (right > left && nums[right] == nums[right + 1]) {
                            right++;
                        }
                    }
                }
            }
        }
        return result;
    }

}
