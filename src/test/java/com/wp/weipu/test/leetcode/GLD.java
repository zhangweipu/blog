package com.wp.weipu.test.leetcode;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * 广联达的笔试
 */
public class GLD extends JS{

    private int aa;


    /**
     *
     * @param i
     */
    public GLD(int i){

    }
    public GLD(){
        this(20);
    }
    public int dance(String a, String b) {
        int len = a.length();
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) == b.charAt(i)) {
                sum += 20;
            } else {
                sum = Math.max(sum - 10, 0);
            }
        }
        return sum;
    }

    @Test
    public void test1() {
        System.out.println(dance("WASDWWSAD", "WASSWWAAD"));
    }

    /**
     * 是所有元素相等
     * 肯定是平衡最大和最小的了
     * 怎么确定最矮，最优的那个
     * 选出最小的，然后算平均
     * 以上都不行
     * 采用循环的方法，每次先排序，最大的补贴最小的
     *
     * @param arr
     * @return
     */
    public int balance(int[] arr) {
        Arrays.sort(arr);
        int i = 0, j = arr.length - 1;
        while (arr[i] != arr[j]) {
            arr[i] += 1;
            arr[j] -= 2;
            if (arr[j] < arr[i]) {
                return -1;
            }
            Arrays.sort(arr);
            if (arr[i] == arr[j]) {
                break;
            }
        }
        return Arrays.stream(arr).sum();
    }


    @Test
    public void test2() {
        int[] arr = new int[]{2, 2, 2, 101};
        System.out.println(balance(arr));
    }

    /**
     * 回溯算法吧
     *
     * @param arr
     * @return
     */
    public int happy(int[][] arr, int sum, int[] color, int power) {
        if (power < 0) {
            return 0;
        }
        if (power == 0) {
            return sum;
        }
        int max = sum;
        for (int i = 0; i < color.length; i++) {
            if (color[i] == 0 && power >= arr[i][0]) {
                color[i] = 1;
                max = Math.max(sum, happy(arr, sum + arr[i][1], color, power - arr[i][0]));
                color[i] = 0;
            }
        }
        return max;
    }

    @Test
    public void test3() {
        int[][] arr = new int[][]{{5, 16}, {9, 1}, {8, 18}};
        int[] color = new int[3];
        System.out.println(happy(arr, 0, color, 15));
    }

    static class Area implements Comparable<Area> {
        public int width;
        public int height;
        public int area;

        public Area(int width, int height) {
            this.width = width;
            this.height = height;
            this.area = width * height;
        }


        @Override
        public int compareTo(Area o) {
            if (o.area > this.area) {
                return -1;
            }
            if (o.area == this.area) {
                double a1 = Math.min((double) o.height / o.width, (double) o.width / o.height);
                double a2 = Math.min((double) height / width, (double) width / height);
                if (a1 > a2) {
                    return 1;
                } else if (a1 == a2) {
                    if (o.width > width) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                return -1;
            }
            return 1;
        }
    }

    @Test
    public void test12() {
        List<Area> areas = new ArrayList<>();
        areas.add(new Area(2, 8));
        areas.add(new Area(8, 2));
        areas.add(new Area(4, 4));
        Collections.sort(areas);
        System.out.println(JSON.toJSONString(areas));
    }

    public static void main(String[] args) {
        Area area = new Area(1, 2);
    }

    /**
     * 做不出来的原因是不懂调整规则
     */
    class Triangle {

    }

    class WorkNode {
        public int dateline;
        public int cost;
        public int sub;

        public WorkNode(int dateline, int cost) {
            this.dateline = dateline;
            this.cost = cost;
            this.sub = Math.abs(cost - dateline);
        }
    }

    /**
     * 获取工作推迟的时间
     * 不对，时间片调度问题吧
     */
    public void getDays(int[][] arr) {
        List<WorkNode> list = new ArrayList<>();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            WorkNode node = new WorkNode(arr[i][0], arr[i][1]);
            list.add(node);
        }

        list.sort(Comparator.comparingInt(o -> o.sub));
        int count = 0;
        int totalCost = 0;
        for (WorkNode node : list) {
            totalCost += node.cost;
            count = Math.max(totalCost - node.dateline + count, count);
        }
        System.out.println(count);
    }

    @Test
    public void test5() {
        int[][] arr = new int[][]{{3, 3}, {8, 1}, {3, 2}};
        getDays(arr);
    }

    /**
     * 求没有公约数
     * 求素数
     *
     * @param a
     * @param b
     * @return
     */
    public boolean isNo(int a, int b) {
        int n = (int) Math.sqrt(a);
        if (b % a == 0) {
            return false;
        }
        while (n > 1) {
            if (a % n == 0 && b % n == 0) {
                return false;
            }
            n--;
        }
        return true;
    }

    /**
     * 格式化数据
     */
    @Test
    public void test() {
        int n = 1;
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(6);
        if (n == 1) {
            System.out.println(numberFormat.format(1.0));
        }
        DecimalFormat df = new DecimalFormat("0.000000");
        df.setRoundingMode(RoundingMode.HALF_UP);
        System.out.println(df.format(2.0 / 3));

        BigDecimal decimal = new BigDecimal(4.0);
        decimal = decimal.setScale(6, RoundingMode.HALF_UP);
        System.out.println(decimal.toString());
        System.out.println(numberFormat.format(2.0 / 6.0));
        for (int i = 2; i < n; i++) {
            if (isNo(i, n)) {
                System.out.println((double) i / (n * 1.0));
                return;
            }
        }


    }

    /**
     * 这题我也没搞定啊啊啊啊啊
     *
     * @param n
     * @param cost
     * @param deadline
     */
    public void getCostTime(int n, int[] cost, int[] deadline) {
        //状态i时的总工作推迟时间
        int[] dp = new int[1 << n];
        for (int i = 0; i < 1 << n; ++i) {
            dp[i] = Integer.MAX_VALUE;
        }
        //状态i时的总耗时
        int maxn = 1 << n;
        int[] sum = new int[maxn];
        dp[0] = 0;
        for (int i = 0; i < maxn; ++i) {
//            for ()
        }
    }

    int[][] dire = new int[][]{{0, 1}, {1, 0}};

    /**
     * 能进入方法的只能是1
     *
     * @param mat
     * @param n
     * @param m
     * @param i
     * @param j
     * @return
     */
    public int getPiece(int[][] mat, int n, int m, int i, int j) {
        //有这个就永远没办法遍历了
//        if (mat[i][j] == -1 || mat[i][j] == 0) {
//            return 0;
//        }
        mat[i][j] = -1;
        for (int[] d : dire) {
            int ii = i + d[0];
            int jj = j + d[1];
            if (ii < n && jj < m && mat[ii][jj] == 1) {
                getPiece(mat, n, m, ii, jj);
            }
        }
        return 0;
    }

    @Test
    public void test9() {
        int[][] mat = new int[][]{{1, 1, 0, 0, 1},
                {1, 0, 0, 1, 0},
                {1, 1, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 1, 0}};
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (mat[i][j] == 1) {
                    count++;
                    getPiece(mat, 5, 5, i, j);
                }
            }
        }

        System.out.println(JSON.toJSONString(mat));

        System.out.println(count);

    }

    /**
     * 没自测，应该有更好的方法
     *
     * @param mat
     * @return
     */
    public boolean isIllegal(char[][] mat) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            //包含重复的数
            if (mat[i][i] != 'X') {
                if (!set.contains(mat[i][i])) {
                    set.add(mat[i][i]);
                } else {
                    return false;
                }
            }
        }
        set.clear();
        for (int i = 8; i >= 0; i--) {
            if (mat[8 - i][i] != 'X') {
                if (!set.contains(mat[8 - i][i])) {
                    set.add(mat[8 - i][i]);
                } else {
                    return false;
                }
            }
        }
        set.clear();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (mat[i][j] != 'X') {
                    if (!set.contains(mat[i][j])) {
                        set.add(mat[i][j]);
                    } else {
                        return false;
                    }
                }
            }
            set.clear();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (mat[j][i] != 'X') {
                    if (!set.contains(mat[j][i])) {
                        set.add(mat[j][i]);
                    } else {
                        return false;
                    }
                }
            }
            set.clear();
        }

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                for (int m = i; m < i + 3; m++) {
                    for (int n = j; n < j + 3; n++) {
                        //这个地方应该修改的，，要用m，n
                        if (mat[m][n] != 'X') {
                            if (!set.contains(mat[m][n])) {
                                set.add(mat[m][n]);
                            } else {
                                return false;
                            }
                        }
                    }
                }
                set.clear();
            }
        }
        return true;
    }

    /**
     * 计算可以分解成质数乘积的个数,质数就是素数吗？？？
     * 如果这个数是质数则返回，不是则继续递归。。。。。
     *
     * @param num
     * @return
     */
    public int fracNum(int num) {
        if (num == 1) {
            return 0;
        }
        if (num == 2 || num == 3) {
            return 1;
        }
        int count = 1;
        double pow = Math.sqrt(num);
        //怎么判断double类型的没有小数
        if (pow % 1 == 0) {
            count = 0;
            count += 2 * fracNum((int) pow);
        } else if (num % 2 == 0) {
            count += fracNum(num / 2);
        } else if (num % 3 == 0) {
            count += fracNum(num / 3);
        }
        return count;
    }

    /**
     * 使用动态规划
     *
     * @param num
     * @return
     */
    public int splitNum(int num) {
        int[] dp = new int[num + 1];
        Arrays.fill(dp, 1);
        for (int i = 3; i <= num; i++) {
            for (int j = 2; j < Math.ceil(Math.sqrt(i)) + 1; j++) {
                if (i % j == 0) {
                    int d = i / j;
                    //这两个一定是比i小的
                    dp[i] = dp[j] + dp[d];
                    break;
                }
            }
        }
        return Arrays.stream(dp).sum() - 2;
    }

    /**
     * 判断质数or素数
     *
     * @return
     */
    public boolean isPrime(int n) {
        int m = (int) Math.sqrt(n);
        while (m > 1) {
            if (n % m == 0) {
                return false;
            }
            m--;
        }
        return true;
    }


    @Test
    public void test51() {
//        int cou = 0;
//        for (int i = 2; i < 100; i++) {
//            if (isPrime(i)) {
//                System.out.println(i);
//                cou++;
//            }
//        }
//        System.out.println(cou);
        System.out.println(splitNum(1000));
    }

    public void reserve(String str) {
        String[] d = str.trim().split("\\s+");
        int len = d.length - 1;
        for (int i = len - 1; i >= 0; i--) {
            if (i == 0) {
                System.out.println(d[i] + d[len]);
            } else {
                System.out.print(d[i] + " ");
            }
        }
    }

    @Test
    public void test20() {
        String a = "I     love   you  .";
        String[] d = a.trim().split("\\s+");

        System.out.println(JSON.toJSONString(d));
        reserve("abc .");
    }

    /**
     * 二分查找
     *
     * @param nums
     * @return
     */
    public long getBalance(int[] nums) {
        long l = 0, r = nums[0] + nums[1] + nums[2] + nums[3];
        while (l < r) {
            long mid = l + (r + 1) / 2;
            if (balance(mid, nums)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        //怎么能返回-1的
        return 4 * (l - 1);
    }

    public boolean balance(long mid, int[] nums) {
        long p = 0, q = 0;
        for (int i = 0; i < 4; i++) {
            if (nums[i] - mid >= 0) {
                p += nums[i] - mid;
            } else {
                q += nums[i] - mid;
            }
        }
        return p + 2 * q >= 0;
    }

    /**
     * 标记法
     *
     * @param nums
     * @return
     */
    public int firstMissingPosition(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (nums[i] < n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    /**
     * 使用动态规划做
     *
     * @param nums
     * @return
     */
    public  boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int i = 0, j = n - 1;
        int a = 0, b = 0;
        while (i < j) {
            if (nums[i] > nums[j]) {
                a += nums[i];
                b += nums[j];
            } else {
                a += nums[j];
                b += nums[i];
            }
            i++;
            j--;
        }
        if (n % 2 != 0) {
            a += nums[i];
        }
        return a >= b ? true : false;
    }
}
