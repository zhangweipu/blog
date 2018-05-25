package com.wp.weipu.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式不理解啊，不理解
 * @author zwp
 */

public class Regext {

    public static String regext(String str){

        return null;
    }

    public static void main(String[] args) {
        String str="oneoo oneeeaaoddoddhj";
        String temp=str.replaceAll("/^one$/","-");
        String[] stri=str.split("/^[one]");
//        for (int i=0;i<stri.length;i++){
//            System.out.println(stri[i]);
//        }
        System.out.println(temp);
        Set<String> set=new HashSet<>();
        set.add("a1");
        set.add("b1");
        set.forEach(System.out::println);
    }

    /**
     * split截出来一个数组会把匹配的移除
     * ?一个 *多个
     *
     */
    @Test
    public void test1(){
        List<String> data= Arrays.asList(
                "data.dat",
                "data1.dat",
                "data2.dat",
                "datax.dat",
                "dataN.dat"
        );
        data.stream().
                forEach(str->{
                    System.out.println(str);
                });

        String a="1347580531633";
        String b="dd.dat";
        boolean is= Pattern.matches("^1[3-9][0-9]\\d{4,8}$",a);
        if (is){
            System.out.println("shi");
        }else {
            System.out.println("bushi");
        }

    }
}
