package com.yang.java8.proxy;

/**
 * 实现类
 *
 * @author yanglizhong
 * @create 2018-08-16 18:22
 */
public class YangServiceImpl implements YangService{
    @Override
    public void likeSomthing(String message) {
        System.out.println("I like " +message);
        eatting("西瓜");
    }

    @Override
    public void eatting(String food) {
        System.out.println(food);
    }
}
