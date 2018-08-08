package com.wp.weipu.test.spider;

import java.util.LinkedList;
import java.util.List;

/**
 * URL队列
 * @author zwp
 */

public class Queue {
    //使用链表实现队列
    private LinkedList queue=new LinkedList();
    //入队
    public void enQueue(Object t){
        queue.add(t);
    }
    //出队列
    public Object deQueue(){
        return queue.removeFirst();
    }
    //判断队列是否为空
    public boolean isQueueEmpty(){
        return queue.isEmpty();
    }
    //判断对象是否包含t
    public boolean contains(Object t){
        return queue.contains(t);
    }

    public boolean empty(){
        return queue.isEmpty();
    }
}
