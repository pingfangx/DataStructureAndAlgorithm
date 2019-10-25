package com.pingfangx.study.tutorial.basic.essential.io.streams;

import org.junit.Test;

/**
 * 格式化参数的长度
 *
 * @author pingfangx
 * @date 2019/10/9
 */
public class FormatArgumentsLengthTest {
    /**
     * %< 指的是前一个参数（而不是它的格式）
     * 3.141593, +00000003.1415926536
     * 1, 1, 2
     * 2, 2, 1
     */
    @Test
    public void test_lowerTo() {
        System.out.format("%f, %<+020.10f %n", Math.PI);
        System.out.format("%1$d, %<d, %2$d %n", 1, 2);
        System.out.format("%2$d, %<d, %1$d %n", 1, 2);
    }

    /**
     * /1000
     * 开始循环 2
     * 结束循环 97
     * 结束 toString 100
     * 结束 format 3664
     * <p>
     * /100
     * 开始循环 19
     * 结束循环 6979
     * 结束 toString 7019
     * 卡了很久也没结束
     */
    @Test
    public void test_format() {
        long start = System.currentTimeMillis();
        //java.lang.OutOfMemoryError: Requested array size exceeds VM limit
        int length = Integer.MAX_VALUE / 100;
        StringBuilder sb = new StringBuilder();
        Object[] args = new Object[length];

        // 0 位
        sb.append("%d");
        args[0] = 0;
        System.out.println("开始循环 " + (System.currentTimeMillis() - start));
        for (int i = 1; i < length; i++) {
            args[i] = i;
            sb.append("\t%d");
        }
        System.out.println("结束循环 " + (System.currentTimeMillis() - start));
        String format = sb.toString();
        System.out.println("结束 toString " + (System.currentTimeMillis() - start));
        String result = String.format(format, args);
        System.out.println("结束 format " + (System.currentTimeMillis() - start));
//        System.out.println(result);
        System.out.println("结束 println " + (System.currentTimeMillis() - start));
    }

    /**
     * java.lang.OutOfMemoryError: Java heap space
     */
    @Test
    public void test_maxArray() {
        for (int i = Integer.MAX_VALUE / 10; i < Integer.MAX_VALUE; i *= 1.1) {
            System.out.println(i);
            Object[] t = new Object[i];
            t = null;
        }
    }
}
