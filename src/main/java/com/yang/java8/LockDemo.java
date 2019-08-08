package com.yang.java8;

import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LockDemo {
    private static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName()+"获得lock开始执行");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName()+"释放锁");
                    lock.unlock();
                }
            });
            thread.start();
        }



        String arr = "15005815525\n" +
                "18603867617\n";
        String[] split = arr.split("\n");
        for (String s : split) {
            if(s.length() != 11 && !isNumeric(s)){

            }else {
                System.out.println(s);
            }
        }
        
    }




    private static final String REG = "[0-9]*";
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile(REG);
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

}
