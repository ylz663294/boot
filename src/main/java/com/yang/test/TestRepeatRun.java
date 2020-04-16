package com.yang.test;/**
 * @author yang
 * @date 2020/04/15
 */

/**
 * @description: if else同时执行。其实是分两次
 * @author: yanglizhong
 * @create: 2020-04-15
 */
public class TestRepeatRun {
    public static void main(String[] args) {
        new TestRepeatRun().print(args == null || new TestRepeatRun() {
            {TestRepeatRun.main(null);}
        }.equals(null));
    }

    public void print(boolean flag) {
        if (flag) {
            System.out.print("a");
        } else {
            System.out.print("b");
        }
    }
}
