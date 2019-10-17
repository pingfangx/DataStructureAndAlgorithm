package com.pingfangx.study.book1.chapter03;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/17
 */
public class BitShiftTest {
    @Test
    public void test_rightShiftAndUnsignedRightShift() {
        int i = -1;
        //11111111111111111111111111111111
        System.out.println(Integer.toBinaryString(i));
        //11111111111111111111111111111111
        //没有变化，因为移动后高位又插入了 1
        System.out.println(Integer.toBinaryString(i >> 1));
        //111111111111111111111111111111
        System.out.println(Integer.toBinaryString(i >>> 1));

        i = -2 << 30;
        //10000000000000000000000000000000
        System.out.println(Integer.toBinaryString(i));
        //11000000000000000000000000000000
        //移动 1 位，补上 1
        System.out.println(Integer.toBinaryString(i >> 1));
        //1000000000000000000000000000000
        System.out.println(Integer.toBinaryString(i >>> 1));
    }

    @Test
    public void test_primaryTypeShift() {
        byte i = (byte) 127;
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));

        //254，移位结果为 int
        System.out.println(i << 1);
        //11111110
        System.out.println(Integer.toBinaryString(i << 1));

        i <<= 1;
        //-2 因为 i 类型不变，又被转为了 byte
        System.out.println(i);
        //11111111111111111111111111111110
        System.out.println(Integer.toBinaryString(i));
    }

    @Test
    public void test_shift() {
        /*
         * 10000000000000000000000000000000
         * 10000000000000000000000000000000
         * 1000000000000000000000000000000000000000000000000000000000000000
         * 1000000000000000000000000000000000000000000000000000000000000000
         * 右操作数只有低5位（对于 Long 是 6 位）有效
         */
        int j = 1;
        System.out.println(Integer.toBinaryString(j << 0b11111));
        System.out.println(Integer.toBinaryString(j << 0b11111_1));
        long l = 1;
        System.out.println(Long.toBinaryString(l << 0b11111_1));
        System.out.println(Long.toBinaryString(l << 0b11111_11));
    }
}
