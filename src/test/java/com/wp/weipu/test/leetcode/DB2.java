package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DB2 {
    public long getNum(int[] arr) {
        Arrays.sort(arr);
        if (arr[0] == 5) {
            return -1;
        }
        StringBuilder sb = new StringBuilder();
        int n = arr.length;
        int zeorn = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == 0) {
                zeorn++;
            }
            sb.append(arr[i]);
        }
        String num = sb.toString();
        long max = Long.parseLong(num);
        if (max % 90 == 0) {
            return max;
        }
        long mut = 1;
        while (max > 0 && mut > 0) {
            if (max % 90 == 0 && isFiveOrZero(max, zeorn)) {
                return max;
            }
            mut = max / 90;
            mut--;
            max = mut * 90;
        }
        return -1;
    }

    public boolean isFiveOrZero(long num, int zero) {
        String n = String.valueOf(num);
        int m = n.length();
        int le = 0, five = 0;
        for (int i = 0; i < m; i++) {
            if (n.charAt(i) == '0') {
                le++;
            } else if (n.charAt(i) == '5') {
                five++;
            } else {
                return false;
            }
        }
        if (le > zero || five > m - zero) {
            return false;
        }
        return true;
    }

    @Test
    public void test() {
        int[] arr = new int[]{5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5};
        System.out.println(getNum(arr));
        System.out.println(isFiveOrZero(555550, 1));
    }

    @Test
    public void test5() {
        StringBuilder sb = new StringBuilder();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            sb.append(i);
            String res = sb.toString();
            ans.add(sb.toString());
        }

        ans.forEach(System.out::print);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        List<String> ans=new ArrayList<>();
        backtrace(ans,root,"");
        return ans;
    }

    public void backtrace(List<String> ans,TreeNode root,String str){
        if(root==null){
            ans.add(str);
            return;
        }
        if("".equals(str)){
            str+=root.val;
        }else{
            str+=("->"+root.val);
        }
        backtrace(ans,root.left,str);
        backtrace(ans,root.right,str);
    }

}
