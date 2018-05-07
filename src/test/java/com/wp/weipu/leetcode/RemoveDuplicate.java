package com.wp.weipu.leetcode;

/**
 *
 */
public class RemoveDuplicate {

    public int removeNum(int []nums){
        int count=0;
        int i=0;
        while(true){
            if((i+count)==nums.length){
                break;
            }
            if(nums[count]==nums[count+1]){
                for(int j=count+1;j<nums.length-1;j++){
                    nums[j]=nums[j+1];
                }
                i++;
            }else {
                count++;
            }
        }
        return count+1;
    }
}
