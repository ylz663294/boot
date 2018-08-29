package com.yang.java8.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 处理类
 *
 * @author yanglizhong
 * @create 2018-08-16 18:24
 */
public class YangInvocationHandle implements InvocationHandler{

    private Object object;

    public YangInvocationHandle(Object o){
        this.object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("$echo")){
            return args[0];
        }
        System.out.println("before method:"+method.getName());
        Object o = method.invoke(object,args);
        System.out.println("after method: "+method.getName());
        return o;
    }
}
