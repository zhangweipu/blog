package com.wp.weipu.test;

import org.junit.Test;

/**
 * @author zwp
 */

public class GetObjectName {


    private class DemoT{
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
    }

    @Test
    public void test() throws NoSuchFieldException {
        System.out.println("weiPUshos ");
    }
}
