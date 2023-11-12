package com.wp.weipu.test.forjob;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Code {

    @Test
    public void quickSort() {
        int[] nums = new int[] {2,3,1,5,6};
        quickSort(nums,0,nums.length -1);
        for (int i: nums) {
            System.out.println(i);
        }
    }

    public void quickSort(int[] nums, int left,int right) {
        if (left >= right) {
            return;
        }
        int l = left, r = right;
        int tmp = nums[left];
        while (left < right) {
            while (left <right && nums[right] >= tmp) {
                right--;
            }
            while (left < right && nums[left] <= tmp) {
                left ++;
            }
            if(left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }

        nums[l] = nums[left];
        nums[left] = tmp;
        quickSort(nums, l, left - 1);
        quickSort(nums, left + 1,r);

    }

    @Test
    public void test5(){
        List<String> strs= new ArrayList<>();
        strs.add("姓名:张三三");
        strs.add("性别:男");
        strs.add("学历:男三三");
        strs.add("学历:学历");
        int index = 1;
        int preLen = 0;
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            if (index%2==0) {
                preLen = sb.length();
                for (int i=0;i<16-preLen;i++) {
                    sb.append("\t");
                }
                System.out.println(sb.length());
                sb.append(s);
                System.out.println(sb);
                sb = new StringBuilder();
            } else {
                sb.append(s);
            }

            index++;
        }

        String a=String.format("%s\t%s","姓名:张三三","性别:男");

        String b=String.format("%s\t%s","学历:男","学历:学历");
        System.out.println(a);
        System.out.println(b);

    }
}
