package com.wp.weipu.test.xiaomi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode {
    /**
     * 盒子中的最大数
     * @return
     */
    public int countBalls(int lowLimit, int highLimit) {
        Map<Integer, Integer> cal = new HashMap<>();
        int max = 0;
        for (int i = lowLimit; i<= highLimit; i++) {
            int box=0,x=i;
            while (x!=0) {
                box += x%10;
                x /= 10;
            }
            cal.put(box, cal.getOrDefault(box, 0) + 1);
            max = Math.max(max, cal.get(box));
        }
        return max;
    }

    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> res = new ArrayList<>();
        for (int a = 0; a <= rowIndex; a++) {
            List<Integer> item = new ArrayList<>();
            for (int j = 0; j<= a ; j++) {
                if (j == 0 || j == a) {
                    item.add(1);
                } else {
                    item.add(res.get(j - 1).get(j - 1) + res.get(j - 1).get(j));
                }
            }
            res.add(item);
        }
        return res.get(rowIndex);
    }

    public List<Integer> getRow2(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add((int) ((long) row.get(i - 1) * (rowIndex - i + 1) / i));
        }
        return row;
    }

    @Test
    public void test(){
        List<Integer> res = getRow2(3);
        for (int i : res) {
            System.out.println(i);
        }
    }

    /**
     * 回溯法
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        boolean[] used =  new boolean[nums.length];
        back(res, item, nums, used, nums.length);
        return res;
    }

    public void back(List<List<Integer>> res, List<Integer> item, int[] nums, boolean[] used, int total) {
        if (item.size() == total) {
            res.add(new ArrayList<>(item));
        }

        for (int i = 0; i< total; i++ ) {
            if (!used[i]) {
                used[i] = true;
                item.add(nums[i]);
                back(res, item, nums, used, total);
            }
            used[i] = false;
            item.remove((item.size() - 1));
        }
    }

    public void backTrack(List<List<Integer>> res, List<Integer> item, int[] nums, int idx, boolean[] used) {
        if (idx == nums.length) {
            res.add(new ArrayList<>(item));
        }

        for (int i = 0; i< nums.length; i++) {
            if (!used[i] || (i>0 && nums[i-1] == nums[i] && used[i-1])){
                continue;
            }
            item.add(nums[i]);
            used[i] = true;
            backTrack(res, item, nums, idx + 1 , used);
            item.remove(idx);
            used[i] = false;
        }
    }

}