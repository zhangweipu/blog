package com.wp.weipu.test;

import java.util.UUID;

/**
 * @author zwp
 */

public class UUIDTest {

    public static String getUUID(){
        String str= String.valueOf(UUID.randomUUID());
        System.out.println(str);

        return str;
    }

    public static void main(String[] args) {
        String str= String.valueOf(UUID.randomUUID());
        System.out.println(str);
    }
}
