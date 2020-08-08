package com.wp.weipu.test.leetcode;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class JD {

    @Test
    public void test() {
        //保留小数
        BigDecimal bg = new BigDecimal(-1.2333633);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(4);
        System.out.println(nf.format(bg));
    }

    @Test
    public void test2() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
//        AtomicInteger sum= new AtomicInteger();
        List<Integer> dd = new ArrayList<>();
        list.forEach(x -> {
//            sum.addAndGet(x);
            dd.add(x);
            System.out.println(x);
        });
        System.out.println(JSON.toJSONString(dd));
        System.out.println(new String()==null);
    }
}
