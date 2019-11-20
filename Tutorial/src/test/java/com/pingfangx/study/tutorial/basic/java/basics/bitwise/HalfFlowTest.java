package com.pingfangx.study.tutorial.basic.java.basics.bitwise;

import org.junit.Test;

/**
 * 取一半的溢出问题
 *
 * @author pingfangx
 * @date 2019/10/27
 */
public class HalfFlowTest {
    /**
     * 不可行，溢出
     */
    private void addAndDivide(int a, int b) {
        printTitle("(a + b) / 2");
        BitwisePrintUtils.printContentAndBinary("a", a);
        BitwisePrintUtils.printContentAndBinary("b", a);
        BitwisePrintUtils.printContentAndBinary("a + b", a + b);
        BitwisePrintUtils.printContentAndBinary("(a + b) / 2", (a + b) / 2);
    }


    /**
     * 可行
     */
    private void addHalfDifferent(int a, int b) {
        printTitle("a + (b - a) / 2");
        BitwisePrintUtils.printContentAndBinary("a", a);
        BitwisePrintUtils.printContentAndBinary("b", b);
        BitwisePrintUtils.printContentAndBinary("b - a", b - a);
        BitwisePrintUtils.printContentAndBinary("(b - a) / 2", (b - a) / 2);
        BitwisePrintUtils.printContentAndBinary("a + (b - a) / 2", a + (b - a) / 2);
    }

    /**
     * 不可行，如果溢出，最高位表示负数的 1 依然存在
     */
    private void signedRightShift(int a, int b) {
        printTitle("(a + b) >> 1");
        BitwisePrintUtils.printContentAndBinary("a", a);
        BitwisePrintUtils.printContentAndBinary("b", b);
        BitwisePrintUtils.printContentAndBinary("a + b", a + b);
        //这里是为了介绍移位运算符的优先级位于加减之后，所以要注意 a+b>>1 与 a<<32-1 之类的运算
        BitwisePrintUtils.printContentAndBinary("a + b >> 1", a + b >> 1);
        BitwisePrintUtils.printContentAndBinary("(a + b) >> 1", (a + b) >> 1);
    }

    /**
     * 可行，考虑溢出就是多进位了一位，通过移位，进位的一位移到了次高位，也从负数变为了正数
     */
    private void unsignedRightShift(int a, int b) {
        printTitle("(a + b) >>> 1");
        BitwisePrintUtils.printContentAndBinary("a", a);
        BitwisePrintUtils.printContentAndBinary("b", b);
        BitwisePrintUtils.printContentAndBinary("a + b", a + b);
        BitwisePrintUtils.printContentAndBinary("a + b >>> 1", a + b >>> 1);
        BitwisePrintUtils.printContentAndBinary("(a + b) >>> 1", (a + b) >>> 1);
    }

    private void printTitle(String method) {
        System.out.println("\n使用 " + method);
    }

    @Test
    public void test() {
        int a = 1 << 30;
        int b = 1 << 30;
        addAndDivide(a, b);
        addHalfDifferent(a, b);
        signedRightShift(a, b);
        unsignedRightShift(a, b);
    }
}
