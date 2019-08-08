package com.yang.collections;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试排序
 *
 * @author yanglizhong
 * @create 2018-03-27 9:59
 */
public class TestSort {
    public static void main(String[] args) {

        List<Double> list = new ArrayList();
        list.add(0.234);
        list.add(0.234);
        list.add(0.237);
        list.add(0.234);
        list.add(0.234);
        list.add(0.234);
        list.add(0.234);
        list.add(0.234);
        list.add(0.234);
        list.add(0.234);

//        Collections.sort(list, new Comparator<Double>() {
//            @Override
//            public int compare(Double driver1, Double driver2) {
//                //剩余空间
//                double a = driver1-driver2;
//                    return a > 0 ? -1 : 1;
//                }
//        });
//        System.out.println(list);

        double d1=0.153;
        double d2 = 11.213;
        System.out.println(d1*d2);
        System.out.println(new BigDecimal(d1).multiply(new BigDecimal(d2)));
        System.out.println(new BigDecimal(String.valueOf(d1)).multiply(new BigDecimal(String.valueOf(d2))).stripTrailingZeros().toPlainString());
        System.out.println(0.1*7);
        System.out.println( new BigDecimal("100.0001").stripTrailingZeros().toPlainString());
    }
}
