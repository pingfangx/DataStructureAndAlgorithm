package com.pingfangx.study.tutorial.basic.java.basics;

import org.junit.Test;

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

    private void bitwise(int a, int b) {
        System.out.println();
        System.out.println("a=" + Integer.toBinaryString(a));
        System.out.println("b=" + Integer.toBinaryString(b));
        System.out.println("a & b=" + Integer.toBinaryString(a & b));
        System.out.println("a | b=" + Integer.toBinaryString(a | b));
        System.out.println("a ^ b=" + Integer.toBinaryString(a ^ b));
    }

    @Test
    public void test_bitwiseOperate() {
        bitwise(0, 0);
        bitwise(0, 1);
        bitwise(1, 0);
        bitwise(1, 1);
    }
}
