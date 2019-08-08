package com.yang.java8;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class TestCollection {


    public static void main(String[] args) {

        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(3);
        list.add(6);
        list.add(2);
        list.add(6);


        Map<String ,String > map = new HashMap<>();
        map.put("1","yang");
        map.put("2","li");
        map.put("3","zhong");


//        Collections.sort(list, new Comparator<Integer>() {
//            public int compare(Integer a1, Integer a2){
//                return a1.compareTo(a2);
//            }
//        });
//      list.sort((a1,a2)->a1.compareTo(a2));
//      list.stream().sorted(Comparator.comparing(TestCollection::comparingByDeliverySort));
        List<Integer> set = list.stream().collect(toList());
        System.out.println(set);

//        Collections.sort(map, new Comparator<Map<String ,String >>() {
//            public int compare(Map a1, Map a2){
//                return a1.compareTo(a2);
//            }
//        });


//        list.forEach(item-> System.out.println(item));

//        map.forEach((k,v)-> System.out.println(k+":"+v));
        String tmp = "ä¸\u008Aæµ·ä¸\u008Aæµ·";
        String str = null;
        try {
            str = new String(tmp.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(str);


    }
    private static Integer comparingByDeliverySort(Integer i) {
        return i;
    }
}
