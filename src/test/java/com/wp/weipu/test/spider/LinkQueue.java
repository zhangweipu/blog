package com.wp.weipu.test.spider;

import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 记录已经访问过的url
 * @author zwp
 */

public class LinkQueue {
    //已访问的url集合
    private static Set visitedUrl=new HashSet();
    //待访问url集合
    private static Queue unVisitedUrl=new Queue();

    //获得url对队列
    public static Queue getUnVisitedUrl(){
        return unVisitedUrl;
    }

    //添加到访问过的url队列
    public static void addVisitedUrl(String url){
        visitedUrl.add(url);
    }

    //移除访问过的url
    public static void removeVisitedUrl(String url){
        visitedUrl.remove(url);
    }

    //未访问的url出对
    public static Object unVisitedUrlDeQueue(){
        return unVisitedUrl.deQueue();
    }
    //保证每个url只能被访问一次
    public static void addUnVisitedUrl(String url){
        if(!StringUtils.isEmpty(url)&& !visitedUrl.contains(url)&& !unVisitedUrl.contains(url)){
            unVisitedUrl.enQueue(url);
        }
    }
    //获取已经访问的url数目
    public static int getVisitedNum(){
        return visitedUrl.size();
    }
    //判断未访问的url队列中是否为空
    public static boolean unVisitedUrlisEmpty(){
        return unVisitedUrl.empty();
    }


}
