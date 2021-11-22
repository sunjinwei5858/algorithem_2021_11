package com.sunjinwei.thread;

/**
 * 面试题：手写打印1-2 1-2 1-2 其实就是生产者消费者 一个线程打印1 一个线程打印2
 * 方法1：单纯使用两个线程，使用synchronized同步 + wait + notify + flag 实现，while循环 需要看wait的源码
 */
public class ProducerAndConsumer01 {

    public volatile boolean flag = true;

    // 1 加锁
    public synchronized void product() throws InterruptedException {

        // 1使用while 防止虚假唤醒
        while (!flag) {
            wait();
        }
        // 2业务逻辑处理
        System.out.println("1");
        flag = false;
        // 3唤醒
        notify();
    }

    public synchronized void consumer() throws InterruptedException {

        // 1使用while 防止虚假唤醒
        while (flag) {
            wait();
        }

        System.out.println("2");
        System.out.println("=====");
        flag = true;
        notify();

    }


    public static void main(String[] args) {
        ProducerAndConsumer01 t = new ProducerAndConsumer01();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    t.product();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    t.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }

    }
}
