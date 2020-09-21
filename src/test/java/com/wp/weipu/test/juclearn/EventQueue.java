package com.wp.weipu.test.juclearn;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class EventQueue {
    static class Event {

    }

    private final LinkedList<Event> queue = new LinkedList<>();
    private final int max = 10;

    public void offer(Event e) {
        synchronized (queue) {
            if (queue.size() > max) {
                System.out.println("队列满了。。。。" + queue.size());
                try {
                    queue.wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("进队了");
            queue.addLast(e);
            //没有wait也能执行
            queue.notify();
        }
    }

    public Event take() {

        Event e = null;
        synchronized (queue) {
            if (queue.isEmpty()) {
                System.out.println("队列是空");
                try {
                    queue.wait();
                } catch (InterruptedException v) {
                    v.printStackTrace();
                }
            }
            e = queue.removeFirst();
            //唤醒消费者线程
            queue.notify();
            System.out.println("出队了 ...");
        }
        return e;
    }

    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        new Thread(() -> {
            for (; ; ) {
                eventQueue.offer(new Event());
            }
        }, "produce").start();
        new Thread(() -> {
            try {
                for (; ; ) {
                    Event event = eventQueue.take();
                    TimeUnit.SECONDS.sleep(5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }


}
