package com.yang.java8.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * 测试
 *
 * @author yanglizhong
 * @create 2018-08-16 18:38
 */
public class Test {
    public static void main(String[] args) {
        YangService yangService = new YangServiceImpl();
        //获取该实例所实现的接口
        Class<?>[] interfaces2 = yangService.getClass().getInterfaces();
        //将EchoService接口添加到Class<?>数组中
        Class<?>[] interfaces = new Class<?>[interfaces2.length + 1];
        interfaces[0] = EchoService.class;
        for (int i = 0; i < interfaces2.length; i++) {
            interfaces[i + 1] = interfaces2[i];
        }

        Object target = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), interfaces, new YangInvocationHandle(yangService));

        YangService ss = (YangService) target;
        ss.likeSomthing("mm");

//        System.out.println("=================================================");
//        //模拟回声
//        EchoService echoServiceProxy = (EchoService) target;
//        //接口必须有实现类，否则会报错。dubbo是通过在filte中拦截，这里是通过方法名判断直接返回实现的
//        System.out.println(echoServiceProxy.$echo("echo"));




        String path = "./aaa.class";
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0",interfaces);
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
