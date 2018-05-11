package com.wp.weipu.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * @author zwp
 */

public class Java8Tester {
    public static void main(String[] args) {
        List<Integer> java8List=new ArrayList<>();
        java8List.add(4);
        java8List.add(2);
        java8List.add(5);
        java8List.add(6);
        java8List.add(9);

        List<Integer> java7List=new ArrayList<>();
        java7List.add(9);
        java7List.add(3);
        java7List.add(1);
        java7List.add(5);
        java7List.add(6);

        eval(java7List,n-> n>3 );

        eval2(java8List,n->1);

        System.out.println();

        java8List.forEach(System.out::println);
    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate){
        for (Integer n:list){
            if(predicate.test(n)){
                System.out.println("predicate:");
                System.out.println(n);
            }
        }
    }

    public static void eval2(List<Integer> list, UnaryOperator<Integer> unaryOperator){


        for (Integer n:list){
            //返回一个对象
            if (unaryOperator.equals(n)){
                System.out.println(unaryOperator);
                System.out.println("eval2:");
                System.out.println(n);
            }
        }
    }

    public static void java8Sort(List<Integer> list){
        Collections.sort(list,(s1,s2)->s1.compareTo(s2));
    }

    public static void java7Sort(List<Integer> list){
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
    }
}
