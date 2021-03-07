package com.wp.weipu.test.review;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;

/**
 * 困难级别的
 */
public class HardQuestion {

    /**
     * 追逐游戏
     *
     * @param edges
     * @param startA
     * @param startB
     * @return
     */
    public int chaseGame(int[][] edges, int startA, int startB) {
        //n个顶点，n条边
        int n = edges.length;
        //todo:这是做什么的
        List<List<Integer>> lists = new ArrayList<>(n + 1);
        //初始化一下
        for (int i = 0; i <= n; i++) {
            lists.add(new ArrayList<>());
        }
        //记录度数
        int[] nums = new int[n + 1];
        for (int[] arr : edges) {
            //无向图
            lists.get(arr[0]).add(arr[1]);
            lists.get(arr[1]).add(arr[0]);
            nums[arr[0]]++;
            nums[arr[1]]++;
        }
        //标记到起点的距离
        int[] distA = new int[n + 1];
        int[] distB = new int[n + 1];
        bfs(lists, distA, startA);
        bfs(lists, distB, startB);
        //找出环
        int ring = n;
        Queue<Integer> queue = new LinkedList<>();
        //添加叶子节点
        for (int i = 1; i <= n; i++) {
            if (nums[i] == 1) {
                queue.add(i);
            }
        }
        //todo:完全不知道是做什么的
        while (!queue.isEmpty()) {
            int f = queue.poll();
            ring--;
            for (int v : lists.get(f)) {
                nums[v]--;
                if (nums[v] == 1) {
                    queue.add(v);
                }
            }
        }
        //追上的步骤
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            if (distA[i] > distB[i] + 1) {
                ans = -1;
                break;
            } else {
                ans = Math.max(ans, distA[i]);
            }
        }
        return ans;
    }

    /**
     * 广度优先的作用是计算距离吗
     *
     * @param list
     * @param dist
     * @param x
     */
    protected void bfs(List<List<Integer>> list, int[] dist, int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        //先初始化为-1
        dist[x] = 0;
        while (!queue.isEmpty()) {
            int f = queue.poll();
            for (int v : list.get(f)) {
                if (dist[v] == 0 && v != x) {
                    dist[v] = dist[f] + 1;
                    queue.add(v);
                }
            }
        }
    }

    /**
     * 掉落的砖块
     * 使用回溯算法
     *
     * @param grid
     * @param hits
     * @return
     */
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int[][] director = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int n = grid.length, m = grid[0].length;
        int[] sum = new int[hits.length];
        int i = 0;
        for (int[] hit : hits) {
            if (hit[1] < 0 || hit[1] >= m || hit[0] < 0 || hit[0] >= n || grid[hit[0]][hit[1]] == 0) {
                sum[i++] = 0;
                continue;
            }
            sum[i++] = brick(hit[1], hit[0], director, grid);
        }
        return sum;
    }

    public int brick(int row, int col, int[][] director, int[][] grid) {
        if (grid[col][row] == 0) {
            return 0;
        }
        int n = grid.length, m = grid[0].length;
        int sum = 1;
        grid[col][row] = 0;
        for (int[] dir : director) {
            int newX = col + dir[0];
            int newY = row + dir[1];
            if (newX < 0 || newX >= n || newY < 0 || newY >= m) {
                continue;
            }
            sum += brick(newY, newX, director, grid);
        }
        return sum;
    }

    @Test
    public void test0() {
        int[][] grid = new int[][]{{1, 0, 0, 0}, {1, 1, 1, 0}};
        int[][] hits = new int[][]{{1, 0}};
        System.out.println(JSON.toJSONString(hitBricks(grid, hits)));
    }

    /**
     * 账户合并
     * 使用并查集
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //存放邮箱对应的用户
        Map<String, List<Integer>> emails = new HashMap<>();
        int n = accounts.size();
        int[] used = new int[n];
        //这句写错了
        for (int i = 0; i < n; i++) {
            List<String> s = accounts.get(i);
            for (int j = 1; j < s.size(); j++) {
                emails.computeIfAbsent(s.get(j), k -> new ArrayList<>()).add(i);
            }
        }
        //这样就错了
        return null;
    }
}
