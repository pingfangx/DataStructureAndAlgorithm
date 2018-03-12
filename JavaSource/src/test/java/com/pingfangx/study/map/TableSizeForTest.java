package com.pingfangx.study.map;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2018/3/9
 */
public class TableSizeForTest {
    static final int MAXIMUM_CAPACITY = 1 << 30;

    @Test
    public void test() {
        /*
        这里参数的选择
        提前加上 1，方法中减 1 就得到了 1 后面一串0
        如果左移 30 次再加 1 就溢出了
        注意括号，先左移，再加 1
         */
        tableSizeFor((1 << 29) + 1);
    }

    static final int tableSizeFor(int cap) {
        printInBinary(cap);
        int n = cap - 1;
        printInBinary(n);
        n |= n >>> 1;
        printInBinary(n);
        n |= n >>> 2;
        printInBinary(n);
        n |= n >>> 4;
        printInBinary(n);
        n |= n >>> 8;
        printInBinary(n);
        n |= n >>> 16;
        printInBinary(n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    private static void printInBinary(int cap) {
        String binary = Integer.toBinaryString(cap);
        if (binary.length() < 31) {
            binary = String.format("%0" + (31 - binary.length()) + "d", 0) + binary;
        }
        System.out.println(binary);
    }
}
