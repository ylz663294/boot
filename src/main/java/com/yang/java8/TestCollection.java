package com.yang.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCollection {


    public static void main(String[] args) {

        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(3);
        list.add(6);
        list.add(2);


        Map<String ,String > map = new HashMap<>();
        map.put("1","yang");
        map.put("2","li");
        map.put("3","zhong");


//        Collections.sort(list, new Comparator<Integer>() {
//            public int compare(Integer a1, Integer a2){
//                return a1.compareTo(a2);
//            }
//        });
        list.sort((a1,a2)->a1.compareTo(a2));
        list.stream().filter().map()
        System.out.println(list);
//        Collections.sort(map, new Comparator<Map<String ,String >>() {
//            public int compare(Map a1, Map a2){
//                return a1.compareTo(a2);
//            }
//        });


//        list.forEach(item-> System.out.println(item));

//        map.forEach((k,v)-> System.out.println(k+":"+v));
    }
}
