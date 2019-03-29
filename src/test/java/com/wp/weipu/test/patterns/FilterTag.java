package com.wp.weipu.test.patterns;

import org.junit.Test;

import java.util.Base64;

public class FilterTag {

    @Test
    public void removeTag() {
        String str = "aaa<a>ddd</a>";
        //中括号里面的^表示非
        str = str.replaceAll("<[^>]+>", "");

        System.out.println(str);

        byte[] bytes = Base64.getEncoder().encode("不可能的".getBytes());
        System.out.println(bytes);
        String a = Base64.getEncoder().encodeToString(bytes);
        System.out.println(a);
        byte[] h = Base64.getDecoder().decode(a);
        System.out.println("h:" + h);
        byte[] b = Base64.getDecoder().decode(bytes);
        System.out.println(b);
        String v = new String(b);
        System.out.println(v);
        String d = String.valueOf(bytes);
        System.out.println(d);


    }
}
