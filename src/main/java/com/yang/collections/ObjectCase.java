package com.yang.collections;

import java.util.concurrent.locks.LockSupport;

/**
 * 对象方法
 *
 * @author yanglizhong
 * @create 2018-08-14 14:02
 */
public class ObjectCase {
    static final Object o = new Object();

    public static void main(String[] args) {
        System.out.println("执行线程："+Thread.currentThread().getName());
        new Thread(new ThreadA(Thread.currentThread())).start();
        System.out.println("before run LockSupport.park()");
//        LockSupport.unpark(Thread.currentThread());
        System.out.println("thread："+Thread.currentThread().getName() +" isInterrupt"+Thread.currentThread().isInterrupted());
        LockSupport.park();
        System.out.println("after run LockSupport.park()");
        System.out.println("thread："+Thread.currentThread().getName() +" isInterrupt"+Thread.currentThread().isInterrupted());

    }
    static class ThreadA implements Runnable{
        Thread thread;
        ThreadA(Thread thread){
            this.thread = thread;
        }

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
            try {
                System.out.println("curent thread name："+Thread.currentThread().getName());
                Thread.sleep(3000);
//                System.out.println("thread："+thread.getName() +" isInterrupt:"+thread.isInterrupted());
                thread.interrupt();
                System.out.println("开始释放线程："+thread.getName());
//                LockSupport.unpark(thread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
