package com.wp.weipu.test.patterns;

import org.junit.Test;

public class FilterTag {

    @Test
    public void removeTag() {
        String str = "aaa<a>ddd</a>";
        //中括号里面的^表示非
        str=str.replaceAll("<[^>]+>","");

        System.out.println(str);

    }
}
