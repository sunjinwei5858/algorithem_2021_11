package com.sunjinwei.thread;

/**
 * 面试题：手写打印1-2 1-2 1-2
 * 方法1：使用synchronized同步 + wait + notify + flag 实现
 *
 */
public class PrintOneByOne {

    public volatile boolean flag = true;

    public synchronized void product() throws InterruptedException {
        if (flag) {
            System.out.println("1");
            flag = false;
            notify();
        } else {
            wait();
        }
    }

    public synchronized void consumer() throws InterruptedException {
        if (!flag) {
            System.out.println("2");
            System.out.println("=====");
            flag = true;
            notify();
        } else {
            wait();
        }
    }


    public static void main(String[] args) {
        PrintOneByOne t = new PrintOneByOne();

        for (int i = 0; i < 10; i++) {
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
