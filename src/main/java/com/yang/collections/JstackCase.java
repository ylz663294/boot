package com.yang.collections;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 线程栈错误
 *
 * @author yanglizhong
 * @create 2018-08-14 11:20
 */
public class JstackCase {
    public static Executor executor = Executors.newFixedThreadPool(5);
    public static Object lock = new Object();

    public static void main(String[] args) {
        Task task1 = new Task();
        Task task2 = new Task();
        executor.execute(task1);
        executor.execute(task2);

    }

    static class Task implements Runnable{

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            synchronized (lock){
                calulate();
            }

        }

        private void calulate() {
            int i = 0;
            while (true){
                System.out.println(i);
                i++;
            }
        }
    }
}
