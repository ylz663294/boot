package com.yang.collections;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * 流测试
 *
 * @author yanglizhong
 * @create 2018-04-16 14:16
 */
public class StreamTest {
    public static void main(String[] args) {
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);

        List<Integer> list = new ArrayList();
        list.add(2);
        list.add(5);
        list.add(9);
        list.add(34);
        list.add(32);
        list.add(22);
        list.add(36);
        list.add(15);
        list.add(12);
        for (int i = 100; i < 1000; i++) {
            list.add(i);
        }
        long timg1 = System.currentTimeMillis();
        List<Integer> s = list.stream().filter((Integer a) -> a > 20).collect(Collectors.toList());
        long timg2 = System.currentTimeMillis();
//        list.sort(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;
//            }
//        });
        list.sort((a, b) -> a.compareTo(b));
        list.sort(comparing(Integer::intValue));
        System.out.println(list + "excet"+(timg2-timg1)+"ms");
//        new Thread(()-> {}).start();

//        Predicate<String> flag = (String hh)-> hh.length()>5;
//        System.out.println(flag.test("123345"));
//
//        Consumer<String> t = (t1)-> System.out.println(t1.toString());
    }
}
