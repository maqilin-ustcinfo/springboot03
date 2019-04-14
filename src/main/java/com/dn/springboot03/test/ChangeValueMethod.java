package com.dn.springboot03.test;

import java.lang.reflect.Field;

public class ChangeValueMethod {

    public static void main(String[] args) {
        Integer a = 10;
        Integer b = 20;
        System.out.println("交换之前：a="+a+",b="+b);
        exchange(a,b);
        System.out.println("交换之后：a="+a+",b="+b);


        Integer c = 50;


    }
    // 交换
    public static void exchange(Integer a,Integer b){
        /*int temp = a;
        a = b;
        b = temp;
        System.out.println("内部交换之后：a="+a+",b="+b);*/
        Class c = Integer.class;
        try {
            Field field = c.getDeclaredField("value");
            field.setAccessible(true);
            Integer temp = new Integer(a);
            // 改变A的值
            field.set(a,b);
            // 改变B的值
            field.set(b,temp);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
