package com.wp.weipu.test.leetcode;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ForOffer1 {
    /**
     * 找出target的起始位置
     * log(n)级别
     * 二分查找
     * 这种就不是二分了。。。。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        //不是logn级别的
        //String s="sss";
        //先不用递归
        //该死的细节
        int n = nums.length;
        if (n == 0) {
            return new int[]{-1, -1};
        }
        //比较中间
        int start = 0, end = n - 1;
        if (nums[start] == target) {
            while (start + 1 <= end && nums[start + 1] == target) {
                start++;
            }
            return new int[]{0, start};
        }
        if (nums[end] == target) {
            while (end - 1 >= 0 && nums[end - 1] == target) {
                end--;
            }
            return new int[]{end, n - 1};
        }
        while (start < end) {
            int mid = (start + end) / 2;
            //三种情况
            if (nums[mid] < target) {
                if (start == mid) {
                    break;
                }
                start = mid + 1;
            }
            if (nums[mid] > target) {
                if (end == mid) {
                    break;
                }
                end = mid - 1;
            }
            if (nums[mid] == target) {
                end = mid;
                start = mid;
                //向前找找
                while (start - 1 >= 0 && nums[start - 1] == target) {
                    start--;
                }
                //向后找找
                while (end + 1 < n && nums[end + 1] == target) {
                    end++;
                }
                break;
            }
        }
        if (start <= end && nums[start] == nums[end] && nums[start] == target) {
            return new int[]{start, end};
        }
        return new int[]{-1, -1};
    }

    @Test
    public void test1() {
        int[] n = new int[]{1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 4, 5, 5, 5, 6, 7, 8, 8, 9, 9, 9, 10};
        System.out.println(JSON.toJSONString(searchRange(n, 7)));
    }

    /**
     * 向右向下走走
     * 我准备用递归
     * 利用动态规划
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    @Test
    public void test2() {
        int res = uniquePaths(3, 2);

        System.out.println(res);
    }

    /**
     * 回溯超时
     *
     * @param m
     * @param n
     * @param i
     * @param j
     * @return
     */
    public int run(int m, int n, int i, int j) {
        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        if (i >= m || j >= n) {
            return 0;
        }
        int[][] direction = new int[][]{{0, 1}, {1, 0}};
        int res = 0;
        for (int[] dire : direction) {
            int newX = i + dire[0];
            int newY = j + dire[1];
            //判断边界
            if (newX == m - 1) {
                res += 1;
                continue;
            }
            if (newY == n - 1) {
                res += 1;
                continue;
            }
            res += run(m, n, newX, newY);
        }
        return res;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        if (candidates.length == 0) {
            return lists;
        }
        if (target == 0 && candidates[0] > 0) {
            return lists;
        }
        Stack<Integer> stack = new Stack<>();
        combinationSum(candidates, target, stack, 0, lists);
        return lists;
    }

    public void combinationSum(int[] candidates, int target,
                               Stack<Integer> stack, int index, List<List<Integer>> list) {
        if (target == 0) {
            list.add(new ArrayList<>(stack));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (target >= candidates[i]) {
                stack.push(candidates[i]);
                combinationSum(candidates, target - candidates[i], stack, i, list);
                stack.pop();
            }
        }
    }

    @Test
    public void test6() {
        int[] arr = new int[]{2, 3, 6, 7};
        System.out.println(JSON.toJSONString(combinationSum(arr, 7)));
    }

    //判断是否重复
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        //第一个条件
        for (int i = 1; i * 2 <= n; ++i) {
            //第二个条件
            if (n % i == 0) {
                boolean match = true;
                for (int j = i; j < n; ++j) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void test7() {

    }

    //返回螺旋数组
    public int[][] generateMatrix(int n) {
        if (n == 0) {
            return new int[0][0];
        }
        int[][] mat = new int[n][n];
        int c = 0;
        //向上取整啊
        int count = 1;
        while (c <= (n + 1) / 2) {
            for (int i = c; count <= n * n && i < n - c; i++) {
                mat[c][i] = count;
                count++;
            }
            for (int i = c + 1; count <= n * n && i < n - c; i++) {
                mat[i][n - c - 1] = count;
                count++;
            }
            for (int i = n - c - 2; count <= n * n && i >= c; i--) {
                mat[n - c - 1][i] = count;
                count++;
            }
            for (int i = n - c - 2; count <= n * n & i > c; i--) {
                mat[i][c] = count;
                count++;
            }
            c++;
        }
        return mat;
    }

    @Test
    public void test09() {
        int[][] arr = generateMatrix(1);
        System.out.println(JSON.toJSONString(arr));
    }

    /**
     * 跳跃的次数最短
     * 那个只是跳跃最远的距离。。。。
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            //
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    /**
     * 思路是错的。。。。错远了。。。
     *
     * @param nums
     * @param i
     * @param times
     * @return
     */
    public int jump(int[] nums, int i, int pre, int times) {
        if (i == nums.length - 1) {
            return times;
        }
        if (i >= nums.length) {
            return Integer.MAX_VALUE;
        }
        int time = Integer.MAX_VALUE;
        for (int j = 0; j < nums.length; j++) {
            //碰到来回跳的。。。怎么把这个改对
            if (j != i && i + nums[j] != pre) {
                time = Math.min(jump(nums, i + nums[j], i, times + 1), time);
            }
        }
        return time;
    }

    @Test
    public void test5() {
        int[] arr = new int[]{1, 2};
        System.out.println(jump(arr));
        String s = "d\\ zhong \" ";
        for (char a : s.toCharArray()) {
            System.out.println(a);
        }
    }

    @Test
    public void test8() {
        String[][] arr = new String[][]{{"d1", "d0", "IT"}, {"d2", "d0", "RD"}, {"d0", "", "The Company"}
                , {"d3", "d0", "HR"}};
        List<DepartTree> list = new ArrayList<>();
        for (String[] f : arr) {
            list.add(new DepartTree(f[0], f[1], f[2]));
        }
        getDepartTree(list);
    }

    public List<String> getDepartTree(List<DepartTree> list) {
        Map<String, List<DepartTree>> map = list.stream().collect(
                Collectors.groupingBy(DepartTree::getPid));
        //怎么对map里的数进行排序
        DepartTree root = map.get("").get(0);
        Stack<DepartTree> queue = new Stack<>();
        queue.add(root);
        List<String> res = new ArrayList<>();
        res.add(root.getName());
        List<String> l = new ArrayList<>();
        //深度遍历
        while (!queue.isEmpty()) {
            DepartTree tmp = queue.pop();
            l.add(tmp.getName());
            List<DepartTree> li = map.get(tmp.id);
            if (li != null && li.size() > 0) {
                queue.addAll(li);
            }
        }

        return l;
    }

    class DepartTree {
        public String id;
        public String pid;
        public String name;

        public DepartTree(String id, String pid, String name) {
            this.id = id;
            this.pid = pid;
            this.name = name;
        }

        public String getId() {
            return id;
        }


        public String getPid() {
            return pid;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public int game(int[] guess, int[] answer) {
        Arrays.sort(guess);
        Arrays.sort(answer);
        int time = 0;
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == answer[i]) {
                time++;
            }
        }
        return time;
    }
}
