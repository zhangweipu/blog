package com.wp.weipu.test;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * @author zwp
 */

public class Test {

    static class MyMap<K,V> extends HashMap<K,V>{
        @Override
        public String toString(){
            Iterator<Entry<K,V>> i = entrySet().iterator();
            if (! i.hasNext())
                return "{}";
            StringBuilder sb = new StringBuilder();
            sb.append('{');
            for (;;) {
                Entry<K,V> e = i.next();
                K key = e.getKey();
                V value = e.getValue();
                sb.append(key   == this ? "(this Map)" :"\""+key+"\"");
                sb.append(':');
                sb.append(value == this ? "(this Map)" :"\""+value+"\"");
                if (! i.hasNext())
                    return sb.append('}').toString();
                sb.append(',').append(' ');
            }
        }
    }

    public static void main(String[] args) throws IOException {
        MyMap myMap=new MyMap();
        myMap.put("id","2");
        myMap.put("name","jerry");

        MyMapDemo myMapDemo=JsonToObject.mapper.readValue(myMap.toString(),MyMapDemo.class);
        System.out.println(myMap.toString());
        System.out.println("muuuu"+myMapDemo.getId());
        Map<String,String> m=new HashMap<>();
        m.put("id","1");
        m.put("name","tom");
        System.out.println(m.toString());

//        List<String> list=new ArrayList<>();
//        list.add("tom");
//        list.add("jerry");
//        list.add("jack");
//        list.add("roce");
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

//        Iterator iterator=list.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//            iterator.remove();
//        }
    }


    @org.junit.Test
    public void test2(){
        Demo demo=new Demo();
        StringUtils.isEmpty(demo.getJson().getStr());
    }


}
