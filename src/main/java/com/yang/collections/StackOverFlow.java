package com.yang.collections;

/**
 * 栈溢出
 *
 * @author yanglizhong
 * @create 2018-08-06 14:54
 */
public class StackOverFlow {
    private static long count = 0;

    public static void main(String[] args) {
        infinitelyRecursiveMethod(1);
    }
    public static void infinitelyRecursiveMethod(long a){
        System.out.println(count++);
        infinitelyRecursiveMethod(a);
    }
}
