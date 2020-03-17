package com.wp.weipu.test.question;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test1 {

    public List<String> generateParenthesis(int n) {
        List<String> esis = new ArrayList<>();
        backforward(esis, "", 0, 0, n);
        return esis;
    }

    public void backforward(List<String> ess, String ca, int in, int out, int n) {
        if (ca.length() == 2 * n) {
            ess.add(ca);
            return;
        }
        if (in < n) {
            backforward(ess, ca + "(", in++, out, n);
        }
        if (in > out) {
            backforward(ess, ca + ")", in, out++, n);
        }
    }

    /*
    无重复子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subset = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> sub = new ArrayList<>(nums[i]);
            subset.add(sub);
            for (int j = i+1; j < nums.length; j++) {
                sub.add(nums[j]);
                sub = new ArrayList<>(sub);
                subset.add(sub);
            }
        }
        return subset;
    }

    @Test
    public void test1() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> sub = subsets(nums);
        System.out.println(sub.toString());
    }

    public static void main(String[] args) {
        System.out.println(1 & 1);
        System.out.println(Integer.toBinaryString(-3));
        System.out.println("11111111111111111111111111111101".length());
    }
}
