package com.pingfangx.datastructure.book01.chapter03;

import com.pingfangx.datastructure.common.util.LogUtils;

import java.util.Random;

/**
 * @author pingfangx
 * @date 2017/11/6
 */
public class A_3_1 {
    public static void main(String[] args) {
        Random random = new Random();
        int n = random.nextInt(100);
        conversion(n, 8);
    }

    private static void conversion(int number, int radix) {
        int original = number;
        Stack stack = new Stack();
        while (number != 0) {
            stack.push(number % radix);
            number = number / radix;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.empty()) {
            stringBuilder.append(stack.pop());
        }
        String result = stringBuilder.toString();
        LogUtils.d("number is %d,result is %s,convert by %d is %d", original, result, radix, Integer.parseInt(result, radix));
    }
}
