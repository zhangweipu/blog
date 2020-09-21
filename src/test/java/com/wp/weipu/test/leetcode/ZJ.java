package com.wp.weipu.test.leetcode;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;

public class ZJ {
    public int getNums(String a, String b) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : a.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }
        int sum = 0;
        for (char c : b.toCharArray()) {
            if (map.getOrDefault(c, 0) > 0) {
                sum++;
                map.merge(c, -1, Integer::sum);
            }
        }
        return sum;
    }

    @Test
    public void test() {
        System.out.println(getNums("AABC", "ABC"));
    }

    /**
     * t使用技能的次数
     *
     * @param grid
     * @param x
     * @param y
     * @param t
     * @return
     */
    static int[][] direct = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int time = Integer.MAX_VALUE;
    static boolean leave = false;

    public static int nums(String[][] grid, int x, int y, int t) {
        int n = grid.length;
        int m = grid[0].length;

        for (int[] dir : direct) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX < 0 || newX >= n || newY < 0 || newY >= m) {
                time = Math.min(time, t);
                leave = true;
                return time;
            }

            if (grid[newX][newY].equals("#")) {
                continue;
            }
            String tmp = grid[newX][newY];

            if (grid[newX][newY].equals("*")) {
                grid[newX][newY] = "#";
                nums(grid, newX, newY, t + 1);
            } else {
                grid[newX][newY] = "#";
                nums(grid, newX, newY, t);
            }
            grid[newX][newY] = tmp;
        }
        //怎么判断走出去
        return leave ? time : -1;
    }

    @Test
    public void test1() {
        String[][] a = new String[][]{{"#", "#", "#"},
                {"#", "@", "#"},
                {"*", "#", "*"}};
        int ans = nums(a, 1, 1, 0);
        System.out.println(ans);

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int k = 0; k < t; k++) {
            int n = in.nextInt();
            int m = in.nextInt();
            String[][] aa = new String[n][m];
            int x = 0, y = 0;
            for (int i = 0; i < n; i++) {
                String line = in.next();
                //循环太多了。。
                for (int j = 0; j < m; j++) {
                    aa[i][j] = String.valueOf(line.charAt(j));
                    if (aa[i][j].equals("@")) {
                        x = i;
                        y = j;
                    }
                }
            }
            int ans = nums(aa, x, y, 0);
            System.out.println(ans);
            List<Integer> ns = new ArrayList<>();
        }
    }

    public int[][] findContinuousSequence(int target) {
        //肯定要小于目标数的一半。能用动态规划做吗
        List<int[]> ans = new ArrayList<>();
        //求和最大的一个数就是目标数的一半
        int end = target / 2 + 1;
        //使用滑动窗口
//        for(int i=1;i<end;i++){
//            int tmp=(i+end)*(end-i+1)/2;
//            int j=end;
//            while(tmp>target && i<j){
//                j--;
//                tmp=(i+j)*(j-i+1)/2;
//            }
//            if(tmp==target){
//                List<Integer> s=new ArrayList<>();
//                for(int m=i;m<=j;m++){
//                    s.add(m);
//                }
//                ans.add(s);
//            }
//        }
        //使用双指针
        int i = 1, j = i;
        int sum = 0;
        while (i < end) {
            if (sum < target) {
                sum += j;
                j++;
            } else if (sum > target) {
                sum -= i;
                i++;
            } else {
                int[] m = new int[j - i];
                int i1 = 0;
                for (int k = i; k < j; k++) {
                    m[i1++] = k;
                }
                ans.add(m);
                sum -= i;
                i++;
            }
        }
        return ans.toArray(new int[0][]);
    }

    @Test
    public void test4() {
        int[][] res = findContinuousSequence(9);
        System.out.println(JSON.toJSONString(res));
    }

    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int min = 0, max = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                continue;
            }
            min = Math.min(min, nums[i]);
            max = Math.min(max, nums[i]);
            if (set.contains(nums[i])) {
                return false;
            }
            set.add(nums[i]);
        }
        return max - min < 5;
    }

    /**
     * 层次遍历树
     *
     * @param root
     * @return
     */

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        //这几种方法的区别回忆一下
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> res = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode r = queue.poll();
                assert r != null;
                res.add(r.val);
                if (r.left != null) {
                    queue.add(r.left);
                }
                if (r.right != null) {
                    queue.add(r.right);
                }
            }
            ans.add(res);

        }
        return ans;
    }
}
