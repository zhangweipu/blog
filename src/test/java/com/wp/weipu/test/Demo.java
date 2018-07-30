package com.wp.weipu.test;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author zwp
 */

public class Demo {

    private class Person implements Comparator<Person> {
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"name\":\"")
                    .append(null==name ? "":name).append('\"');
            sb.append(",\"age\":")
                    .append("\""+ (null==age ? "":age)+"\"");
            sb.append('}');
            return sb.toString();
        }


        @Override
        public int compare(Person o1, Person o2) {
            int flag=0;
            if(o1.getName().equals(o2.getName())){
                flag=0;
            }
            return flag;
        }
    }

    public Json getJson(){
        System.out.println("getjson");
        String str="wssss";
        char a='a';
        return new Json();
    }

    @Test
    public void ListToJson(){
        List<Person> persons=new ArrayList<>();
        for(int i=0;i<4;i++){
            Person person=new Person();
            person.setAge(i);
            person.setName("tom"+i);
            persons.add(person);
        }
        Person person=new Person();
        person.setName("sss");
        persons.add(person);
        System.out.println(persons.toString());
    }

    @Test
    public void tests() throws UnsupportedEncodingException {
        Set<Person> personSet=new HashSet<>();

        for (int i=0;i<3;i++){
            Person person=new Person();
            person.setName("tom");
            person.setAge(5);
            personSet.add(person);
        }

//        System.out.printf(personSet.size()+"");
//
//        String aa="sss";
//        System.out.println(aa.substring(0,20)+"2222");

        String bb="%7B%22projectid%22%3A%2232104%22%2C%22projectguid%22%3A%222195FEF7-3A36-45AA-9A3C-B246312B564E%22%2C ";
        System.out.printf(new String(bb.getBytes("ISO-8859-1"), "utf-8"));

    }
}
