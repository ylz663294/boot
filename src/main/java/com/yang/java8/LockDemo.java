package com.yang.java8;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    private static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName()+"获得lock开始执行");
//                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName()+"释放锁");
                    lock.unlock();
                }
            });
            thread.start();
        }
    }

}
