package com.wp.weipu.test;

import com.rabbitmq.client.UnblockedCallback;
import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * java8的lambda的用法
 * <p>
 * Stream()的操作集合
 * List<String> lastStoneList =
 * stoneLine.stream()
 * .filter(s -> s.getWeight() < 500)//挑选出质量小于500g的鹅卵石
 * .sorted(comparing(Stone::getWeight))//按照质量进行排序
 * .map(Stone::getName)//提取满足要求的鹅卵石的名字
 * .collect(toList());//将名字保存到List中
 *
 * @author zwp
 */

public class Lambda implements ILambda {

    @Override
    public String func(String a, String b) {
        return a + b;
    }

    /**
     * （一）lambda实现匿名类，不懂
     */

    /**
     * （二）lambda进行事件处理
     */

    /**
     * （三）lambda对列表进行迭代
     */
    @Test
    public void test3() {
        List<String> list = Arrays.asList("tom", "jerry", "jack");
        list.forEach(l -> {
            System.out.println(l);
        });
        list.forEach(System.out::println);
        list.forEach(l -> {
            String a = String.valueOf(l);
            System.out.println(a);
        });
    }

    /**
     * (四) lambda结合Predicate实现过滤
     */
    /**
     * 过滤方法
     */
    public static void filter(List<String> names, Predicate<String> condition) {
        //第一种过滤
        names.forEach(name -> {
            if (condition.test(name)) {
                System.out.println(name + "yy");
            }
        });
        //第二种过滤 better
        names.stream().filter((name) -> (condition.test(name))).forEach(name -> {
            System.out.println(name);
        });
    }

    /**
     * 实现过滤
     */

    @Test
    public void test4() {
        List<String> names = Arrays.asList("java", "python", "c++", "scala", "c", "php");
        System.out.println("names which starts with j");
        /******names列表*****验证条件*************/
        filter(names, (str) -> str.startsWith("j"));
    }

    /**
     * 使用Predicate多重过滤
     * java.util.function.Predicate 允许将两个或更多的 Predicate
     * 合成一个。它提供类似于逻辑操作符AND和OR的方法，名字叫做and()、
     * or()和xor()，用于将传入 filter() 方法的条件合并起来。例如
     * ，要得到所有以J开始，长度为四个字母的语言，可以定义两个独立的
     * Predicate 示例分别表示每一个条件，然后用 Predicate.and() 方法将它们合并起来
     */
    @Test
    public void test5() {
        //两个条件
        Predicate<String> startWithJ = (n) -> n.startsWith("j");
        Predicate<String> fourLong = (n) -> n.length() == 4;
        //开始过滤
        List<String> names = Arrays.asList("java", "python", "c++", "scala", "c", "php");
        names.stream()
                .filter(startWithJ.and(fourLong))
                .forEach(name -> System.out.println(name));
    }

    /**
     * lambda中的map允许将对象进行转换
     * int 到 double
     * reduce 有count,average ,sum要自己写
     */
    @Test
    public void test6() {
        List<Integer> cost = Arrays.asList(100, 200, 300);
        cost.stream()
                .map((x) -> x + 0.12 * x)
                .forEach(System.out::println);
        //求和
        Double sum1 = cost.stream()
                .map((x) -> x + 0.12 * x)
                .reduce((sum, x) -> sum + x)
                .get();
    }

    /**
     * 对列表中的每一个元素应用函数
     * 使用map可以把list转到String
     */
    @Test
    public void test7() {
        List<String> names = Arrays.asList("java", "python", "c++", "scala", "c", "php");
        String str = names.stream()
                .map((x) -> x.toUpperCase())
                .collect(Collectors.joining(","));
        System.out.println(str);
    }

    /**
     * 复制不同的值创建一个子列表
     * distinct() 方法来对集合进行去重
     */
    @Test
    public void test8() {
        List<Integer> numbers=Arrays.asList(1,2,3,4,3,5,5,7);
        List<Integer> dis=numbers.stream()
                .map(i->i*i)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(dis);
    }

    /**
     * 计算集合元素的最大值、最小值、总和、平均值
     */

    @Test
    public void test9(){
        List<Integer> numbers=Arrays.asList(1,2,3,4,3,5,5,7);
        IntSummaryStatistics stats=numbers.stream()
                .mapToInt((x)->x)
                .summaryStatistics();
        System.out.println("平均："+stats.getAverage());
        System.out.println("max:"+stats.getMax());
    }

}
