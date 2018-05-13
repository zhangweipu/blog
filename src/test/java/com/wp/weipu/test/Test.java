package com.wp.weipu.test;

import java.util.*;

/**
 * @author zwp
 */

public class Test {
    public static void main(String[] args) {
        Map<String,String> m=new HashMap<>();
        List<String> list=new ArrayList<>();
        list.add("tom");
        list.add("jerry");
        list.add("jack");
        list.add("roce");
//        boolean flag=true;
//        //这种通过在for中删除的做法不对
//        for(int i=0;i<4;i++){
//            if(i==2 && flag){
//                System.out.println("remove second"+list.get(i));
//                list.remove(i);
//                i--;
//                flag=false;
//            }
//            System.out.println(i);
//            System.out.println(list.get(i));
//        }

        Iterator iterator=list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            iterator.remove();
        }
    }
}
