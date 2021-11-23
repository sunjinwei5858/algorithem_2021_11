package com.sunjinwei.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 方法2: lock+condition+await+signal+while
 * lock的特点就是可以利用condition实现精准唤醒
 */
public class ProducerAndConsumer02 {

    private Lock lock = new ReentrantLock();
    private Condition produceCondition = lock.newCondition();
    private Condition consumerCondition = lock.newCondition();

    private boolean flag = true;

    public void product() throws InterruptedException {
        lock.lock();
        try {
            while (!flag) {
                produceCondition.await();
            }
            System.out.println("=====================" + "1");
            flag = false;
            consumerCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try {
            while (flag) {
                consumerCondition.await();
            }
            System.out.println("=====================" + "2");
            flag = true;
            produceCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        ProducerAndConsumer02 producerAndConsumer02 = new ProducerAndConsumer02();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    producerAndConsumer02.product();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    producerAndConsumer02.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }

}
