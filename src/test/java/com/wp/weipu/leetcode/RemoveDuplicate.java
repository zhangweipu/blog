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

    /**
     * 字符串反转，StringBuffer中有反转方法,效率会不高
     * new的时候
     * @param s
     * @return
     */
    public static String reverseString(String s) {
        if(s.length()==0){
            return "";
        }
        //StringBuffer sb=new StringBuffer();
        char []str=s.toCharArray();
        char []res=new char[str.length];
        for(int i=str.length-1;i>=0;i--){
            res[str.length-i-1]=str[i];
        }
        return new String(res);
    }

    /**
     * 哈哈哈，我服我的操作
     * @param x
     * @return
     */
    public static int reverse(int x) {
        String t="";
        if(x<0){
            x=Math.abs(x);
            t+="-";
        }
        String str=String.valueOf(x);
        String s=t+new StringBuffer(str).reverse().toString();
        int num=0;
        try {
            num=Integer.valueOf(s.trim());
        } catch (Exception e) {
            return 0;
        }

        if(num < (-Math.pow(2,31)) || num >(Math.pow(2,31)-1)){
            return 0;
        }
        return num;
    }

    /**
     * 第一个不重复的字符
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        char []str=s.toCharArray();
        int length=str.length;
        int index=0;
        for (int i=0;i<length;i++){

        }

        return 0;
    }

    public static void main(String[] args) {
        int i=-12;
        reverse(i);
    }
}
