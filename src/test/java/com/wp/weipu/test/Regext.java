package com.wp.weipu.test;

import java.util.HashSet;
import java.util.Set;

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
}
