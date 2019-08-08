package com.yang.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程测试类
 *
 * @author yanglizhong
 * @create 2018-09-03 15:52
 */
public class ThreadPoolTest {
    public static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        for (int i=0;i<1;i++){

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("1:"+Thread.currentThread().getName());
//                    System.out.println(1/0);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("2:"+Thread.currentThread().getName());
                }
            });
        }
    }
}
