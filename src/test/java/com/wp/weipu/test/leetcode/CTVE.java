package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Pattern;

public class CTVE {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] graph = new int[n][n];
        for (int[] f : flights) {
            graph[f[0]][f[1]] = f[2];
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        //k+1还可以走过的站点。。。？？？？
        heap.offer(new int[]{0, src, k + 1});
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int price = cur[0], place = cur[1], remainStops = cur[2];
            //怎么获取最短的路径。。。。 这个没办法获得路径。。。
            if (place == dst) {
                return price;
            }
            //这个就相当于w，不能无线的长。。。
            if (remainStops > 0) {
                for (int i = 0; i < n; i++) {
                    //判断两个点是否存在路径
                    if (graph[place][i] > 0) {
                        heap.offer(new int[]{price + graph[place][i], i, remainStops - 1});
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 使用优先队列，和map实现前k个最多的数
     * 今天的想法和官方差不多
     * 直接加set里面不就好了
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.merge(nums[i], 1, Integer::sum);
        }
        int[][] arr = new int[map.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            arr[i][0] = entry.getKey();
            arr[i][1] = entry.getValue();
            i++;
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (int[] l : arr) {
            heap.offer(l);
        }
        int[] ans = new int[k];
        for (int j = heap.size() - 1; j >= 0; j--) {
            int s = heap.poll()[0];
            if (j < k) {
                ans[j] = s;
            }
        }
        return ans;
    }

    public static int genMiMa(String s) {
        boolean nu = false;
        boolean big = false;
        boolean small = false;
        if (s.length() < 8 || s.length() > 120) {
            System.out.println("Irregular password");
            return 0;
        }
        boolean isMatch = Pattern.matches(".*?[~\\W+|_].*?", s);
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                small = true;
            }
            if (c >= 'A' && c <= 'Z') {
                big = true;
            }
            if (c >= '0' && c <= '9') {
                nu = true;
            }
        }
        if (isMatch && nu && small && big) {
            System.out.println("Ok");
        } else {
            System.out.println("Irregular password");
        }

        return 0;

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.next();
            genMiMa(str);
        }
    }

    @Test
    public void test3() {
        String a1 = "12,Aaqq12";
        String a2 = "Passw,ord123";
        String a3 = "PASSWORD_123";
        String a4 = "PaSS^word";
        String a5 = "12_Aa11";
        genMiMa(a1);
        genMiMa(a2);
        genMiMa(a3);
        genMiMa(a4);
        genMiMa(a5);
    }

    @Test
    public void test4() {
//        int[] arr = new int[]{1, 1, 1, 2, 2, 3};
//        int[] ans = topKFrequent(arr, 2);
//        System.out.println(Arrays.toString(ans));
        String aa = "12Aaqq12\\";
        boolean isMatch = Pattern.matches(".*?[~\\W+|_].*?", aa);
//        genMiMa(aa);
        System.out.println(isMatch);
    }

    /**
     * 这样做不行，有错 1，3，6，7，9，4，10，5，6 不能通过
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.empty() && stack.peek() > nums[i]) {
                stack.pop();
            }
            stack.push(nums[i]);
            max = Math.max(max, stack.size());
        }
        return max;
    }

    /**
     * 使用动态规划做
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] count = new int[n];
        //每一个有序最少是一
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);
        int max = 0;
        for (int i = 0; i < n; i++) {
            // 0-5 1-5 可能会存在两个路径
            for (int j = 0; j < i; j++) {
                //满足增序的情况
                if (nums[i] > nums[j]) {
                    //这是第一次长度相同的情况
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                        //他们俩相等所以就不用继续累加了
                    } else if (dp[j] + 1 == dp[i]) {
                        //有一次长度相同
                        count[i] += count[j];
                    }
                }
            }
            //找出最长的
            max = Math.max(max, dp[i]);
        }
        int ans = 0;
        for (int m = n - 1; m >= 0; m--) {
            if (max == dp[m]) {
                ans += count[m];
            }
        }
        return ans;
    }

    @Test
    public void test5() {
        int[] n = {1, 3, 5, 4, 7};
        System.out.println(lengthOfLIS2(n));
    }

    /**
     * 俄罗斯套娃问题 w宽h高
     * 我先用优先队列排序，然后对数组的前缀查找最长增序
     * 这个遇到w相等的或者h相等的就歇菜了
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        //序列的长度
        int n = envelopes.length;
        //排序
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (int[] ar : envelopes) {
            heap.offer(ar);
        }
        int[] nums = new int[heap.size()];
        int i = 0;
        while (!heap.isEmpty()) {
            nums[i] = heap.poll()[0];
            i++;
        }
        //查找最长有序子序列
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 0;
        for (int l = 0; l < nums.length; l++) {
            for (int j = 0; j < l; j++) {
                if (nums[l] > nums[j]) {
                    if (dp[j] + 1 > dp[l]) {
                        dp[l] = dp[j] + 1;
                    }
                }
            }
            max = Math.max(max, dp[l]);
        }

        return max;
    }

    /**
     * 俄罗斯套娃问题，
     * 先按w排序如果相同按h排序，在对h序列求最长
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes1(int[][] envelopes) {
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                //如果相同就按h倒序
                return o2[1] - o1[1];
            } else {
                return o1[0] - o2[0];
            }
        });

        int n = envelopes.length;
        int[] nums = new int[n];
        int l = 0;
        for (int[] a : envelopes) {
            nums[l] = a[1];
            l++;
        }
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    @Test
    public void test6() {
        int[][] arr = {{1, 5}, {1, 4}, {1, 2}, {2, 3}};
        int a = maxEnvelopes1(arr);
        System.out.println(a);
    }

    /**
     * 求数组最大连续乘积
     * 暴力超出内存限制
     *
     * @param nums
     * @return
     */
    public int maxProduct1(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        //他们为什么会多申请呢
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                dp[j][i] = dp[j][i - 1] * nums[i];
                max = Math.max(max, dp[j][i]);
            }
            max = Math.max(max, dp[i][i]);
        }
        return max;
    }

    /**
     * 动态规划两个数组分别记录，最大值和最小值
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] minNum = new int[n];
        int[] maxNum = new int[n];
        minNum[0] = maxNum[0] = nums[0];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            maxNum[i] = Math.max(nums[i], Math.max(maxNum[i - 1] * nums[i], minNum[i - 1] * nums[i]));
            minNum[i] = Math.min(nums[i], Math.min(maxNum[i - 1] * nums[i], minNum[i - 1] * nums[i]));
            max = Math.max(max, maxNum[i]);
        }
        return Math.max(max, nums[0]);
    }

    /**
     * 环形数组最大连续子数组
     * todo:这个没做对呢
     *
     * @param A
     * @return
     */
    public int maxSubarraySumCircular(int[] A) {
        int n = A.length;
        int[] dp = new int[2 * n];
        int sum = 0;
        int max = Integer.MIN_VALUE;
        dp[0] = A[0];
        //i和j的距离不能大于n
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + A[i], A[i]);
            max = Math.max(max, dp[i]);
            sum += A[i];
        }
        for (int i = n; i < 2 * n; i++) {
            dp[i] = Math.max(dp[i - 1] + A[i % n], A[i % n]);
            max = Math.max(max, dp[i]);
        }
        if (dp[2 * n - 1] == sum) {
            return sum;
        }
        return max;
    }

    /**
     * 使用动态规划做
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        int maxLen = 1;
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        int begin = 0;
        for (int j = 1; j < n; j++) {
            //确保上面的大于下面的啊。。。。。
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        //状态转移
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && maxLen < (j - i + 1)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    @Test
    public void test7() {
        System.out.println(lengthOfLongestSubstring1("aaabkoikttetyuighds"));
    }

    /**
     * 和上面的一样只不过条件变了。。。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLen = 1;
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        int index = 0;
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (!isNoReload(s.substring(i, j + 1))) {
                    //这个做法有些多此一举
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        if (isNoReload(s.substring(i + 1, j - 1))) {
                            dp[i][j] = dp[i + 1][j - 1];
                        }

                    }
                    //不进入这个判断为什么
                    if (dp[i][j] && (j - i + 1) > maxLen) {
                        maxLen = j - i + 1;
                        index = i;
                    }
                }
            }
        }
        return maxLen;
    }

    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        int maxLen = 1;
        int index = 0;
        //这种暴力不是很好
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                String a = s.substring(i, j + 1);
                if (isNoReload(a)) {
                    //这个做法有些多此一举
                    maxLen = Math.max(maxLen, a.length());
                }
            }
        }
        return maxLen;
    }

    public boolean isNoReload(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                return false;
            }
            set.add(s.charAt(i));
        }
        return true;
    }

    /**
     * 岛屿问题
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int num = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    num++;
                    dfs(grid, i, j);
                }
            }
        }
        return num;
    }

    public void dfs(char[][] grid, int i, int j) {
        int n = grid.length;
        int m = grid.length;
        if (i < 0 || i >= n || j < 0 || j >= m || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }

    /**
     * 三角性最短路径
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        int level = 1;
        for (List<Integer> ls : triangle) {
            for (int i = 0; i < level; i++) {

            }
            level++;
        }
        return 0;
    }

    @Test
    public void test09() {
        String n = "101";
        long l = Long.valueOf(n, 2);
        int i;
        for (i = 1; i < 50; i++) {
            if (l < Math.pow(4, i)) {
                break;
            }
        }
        int a = (int) Math.log(l)-1;
        System.out.println(a);
        if (i % 2 == 0) {
            System.out.println(i);
        } else {
            System.out.println(i - 1);
        }
    }
}
