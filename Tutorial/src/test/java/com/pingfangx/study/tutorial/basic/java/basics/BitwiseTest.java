package com.pingfangx.study.tutorial.basic.java.basics;

/**
 * @author pingfangx
 * @date 2019/9/23
 */
public class BitwiseTest {
    public static void main(String[] args) {
        int i = 7;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(i >> 1));
        System.out.println(Integer.toBinaryString(i >>> 1));
        i = -7;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(i >> 10));
        System.out.println(Integer.toBinaryString(i >>> 10));
    }
}
