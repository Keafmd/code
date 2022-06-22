package com.keafmd.springdemo.test;

/**
 * Keafmd
 *
 * @ClassName: StringTest
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-06-07 16:30
 */
public class StringTest {
    public static void main(String[] args) {
        String s1 = "one of Asia's most versatile full-service consultancie";
        String s2 = s1.replaceAll("'","\\\\'");
        s2 = "'"+s2+"'";
        System.out.println(s2);
    }
}
