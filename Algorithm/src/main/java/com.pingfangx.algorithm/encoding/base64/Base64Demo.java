package com.pingfangx.algorithm.encoding.base64;

/**
 * Base64
 * low 到爆的实现，伤心
 * 官方的实现则是使用位运算
 * {@link Base64Util}
 * {@link java.util.Base64}
 *
 * @author pingfangx
 * @date 2018/9/17
 */
class Base64Demo {
    public static final String a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmlnopqrstuvwxyz0123456789+/";

    /**
     * 编码
     */
    public static String encode(String in) {
        //编码
        byte[] bytes = in.getBytes();
        //转为二进制
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            String binaryString = Integer.toBinaryString(b);
            //补齐 8 位
            int add = 8 - binaryString.length();
            for (int i = 0; i < add; i++) {
                stringBuilder.append('0');
            }
            stringBuilder.append(binaryString);
        }
        //补0
        int length = stringBuilder.length();
        int size = length / 6;
        int remain = length % 24;
        if (remain > 0) {
            size += 1;
            int add = 24 - remain;
            for (int i = 0; i < add; i++) {
                stringBuilder.append('0');
            }
        }
        int addLast = stringBuilder.length() / 6 - size;
        //按 6 位截取
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < size; i++) {
            String substring = stringBuilder.substring(i * 6, (i + 1) * 6);
            int index = Integer.parseInt(substring, 2);
            out.append(a, index, index + 1);
        }
        //补 =
        for (int i = 0; i < addLast; i++) {
            out.append('=');
        }
        return out.toString();
    }

    /**
     * 解码
     */
    public static String decode(String in) {
        int length = in.length();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = in.charAt(i);
            if (c == '=') {
                break;
            }
            int index = a.indexOf(c);
            String binaryString = Integer.toBinaryString(index);
            int add = 6 - binaryString.length();
            for (int j = 0; j < add; j++) {
                out.append('0');
            }
            out.append(binaryString);
        }
        int remain = out.length() % 8;
        out.delete(out.length() - remain, out.length());

        //每 8 位组成一个字符
        length = out.length() / 8;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String s = out.substring(i * 8, (i + 1) * 8);
            char c = (char) Integer.parseInt(s, 2);
            result.append(c);
        }

        return result.toString();
    }
}
