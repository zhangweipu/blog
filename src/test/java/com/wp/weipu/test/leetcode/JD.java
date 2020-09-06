package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JD {
    @Test
    public void test() {
        BigDecimal b = new BigDecimal(4);
        b= BigDecimal.valueOf(5,4);
        double a=5.0;
        System.out.println("%4d"+a);
        DecimalFormat dd=new DecimalFormat("#.0000");
        System.out.println(dd.format(a));
    }

    public static double foo(int n){
        double sum=0.0;
        int flag=-1;
        for(int i=1;i<=n;i++){
            if(i%2==0){
                flag=-1;
            }else{
                flag=1;
            }
            sum+=flag*(1.0/(5.0*2.0*i));
        }
        return sum;
    }

    public static void main(String[] args) {
        String ss="a2346";
        for (int i=0;i<ss.length();i++){
            String b=ss.substring(0,i)+ss.substring(i+1,5);
            System.out.println(b);
        }

        int n=110;
        int m=120;
        int sum=0;
        for(int num=n;num<m;num++){
            Set<Integer> list=removeOne(num);
            for(int x:list){
                if(isSushu(x)){
                    sum+=huiwen(x);
                }
            }

        }
        System.out.print(sum);
    }

    public static Set<Integer> removeOne(int num){
        String str=String.valueOf(num);

        int len=str.length()-1;
        Set<Integer> list=new HashSet<>(len+1);
        for(int i=0;i<=len;i++){
            String s=str.substring(0,i)+str.substring(i+1,len+1);
            list.add(Integer.valueOf(s));
        }
        return list;
    }

    public static boolean isSushu(int num){
        int i=2;
        while(i*i<=num){
            int j=num/i;
            if(i*j==num){
                return false;
            }
            i++;
        }

        return true;
    }

    public static int huiwen(int num){
        String str=String.valueOf(num);
        int i=0,j=str.length()-1;
        while(i<j){
            if(str.charAt(i)!=str.charAt(j)){
                return 0;
            }
            i++;
            j--;
        }
        return 1;
    }
}
