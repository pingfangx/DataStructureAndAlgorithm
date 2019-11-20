package com.pingfangx.study.tutorial.basic.java.basics.bitwise;

/**
 * @author pingfangx
 * @date 2019/10/27
 */
public class BitwisePrintUtils {
    private static final int DEFAULT_PREFIX_WIDTH = 20;

    public static void printContentAndBinary(String prefix, int value) {
        printContent(prefix, value);
        printBinary(prefix, value);
    }

    public static void printContent(String prefix, int value) {
        System.out.println(expandPrefix(prefix, DEFAULT_PREFIX_WIDTH)
                + " = "
                + value);
    }

    public static void printBinary(String prefix, int value) {
        System.out.println(expandPrefix(prefix, DEFAULT_PREFIX_WIDTH)
                + " = 0b "
                + toBinaryAndSplitString(value));
    }

    public static String expandPrefix(String prefix, int prefixWidth) {
        return String.format("%" + prefixWidth + "s", prefix);
    }

    public static String toBinaryAndSplitString(int value) {
        return splitString(toBinaryString(value));
    }

    public static String toBinaryString(int value) {
        return toBinaryString(value, 32);
    }

    public static String toBinaryString(int value, int bits) {
        return String.format("%" + bits + "s", Integer.toBinaryString(value))
                .replace(" ", "0");
    }

    private static String splitString(String s) {
        return splitString(s, 8);
    }

    private static String splitString(String s, int bits) {
        StringBuilder sb = new StringBuilder();
        int length = s.length();
        for (int i = 0; i < length; i += bits) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(s, i, Math.min(i + bits, length));
        }
        return sb.toString();
    }

}
