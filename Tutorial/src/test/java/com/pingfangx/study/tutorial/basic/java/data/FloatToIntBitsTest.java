package com.pingfangx.study.tutorial.basic.java.data;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pingfangx
 * @date 2019/10/25
 */
public class FloatToIntBitsTest {
    void floatToIntBits(float value) {
        print("float", value);

        //整数部分
        int integer = (int) value;
        print("int", String.valueOf(integer));
        //整数二进制表示
        String intBinary = Integer.toBinaryString(integer);
        printBinary("int binary", integer, 8);
        int exponent = intBinary.length() - 1;
        String s = new StringBuilder(intBinary)
                .insert(1, '.')
                .append(' ')
                .append('*')
                .append(' ')
                .append(2)
                .append('^')
                .append(exponent)
                .toString();
        print("int", s);
        //指数
        print("exponent", exponent);
        //偏移指数
        int offetExponent = exponent + 127;
        print("offset exponent", offetExponent);
        //符号
        String sign = value < 0 ? "1" : "0";
        print("1 sign", sign);
        //偏移指数
        printBinary("2 offset e binary", offetExponent, 8);

        String mantissa = intBinary.substring(1);
        if (mantissa.length() == 0) {
            mantissa = "0";
        }
        //尾数
        print("3 mantissa", mantissa);
        String result = new StringBuilder("0b ")
                .append(sign)
                .append(' ')
                .append(toBinaryString(offetExponent, 8))
                .append(' ')
                .append(mantissa)
                .toString();
        print("1+2+3", result);

        String floatToIntBitsSplit = new StringBuilder(toBinaryString(Float.floatToIntBits(value), 32))
                .insert(1 + 8, " ")
                .insert(1, " ")
                .insert(0, "0b ")
                .toString();
        print("floatToIntBits", floatToIntBitsSplit);
    }


    private void printBinary(String pre, int value, int bits) {
        print(pre, "0b " + toBinaryString(value, bits));
    }

    private String toBinaryString(int value, int bits) {
        return String.format("%" + bits + "s", Integer.toBinaryString(value))
                .replace(" ", "0");
    }

    private void print(String pre, Object value) {
        System.out.println(String.format("%-20s = %s", pre, value));
    }

    @Test
    public void test() {

        List<Float> testCase = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            testCase.add((float) i);
//            testCase.add(i + 0.5F);
        }
        for (Float o : testCase) {
            System.out.println();
            floatToIntBits(o);
        }
    }
}
