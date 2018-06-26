package com.wp.weipu.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zwp
 */

public class Demo {

    private class Person{
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
}
