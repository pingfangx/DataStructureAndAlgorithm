package com.pingfangx.algorithm.encoding.md5;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5 测试
 *
 * @author pingfangx
 * @date 2018/9/17
 */
public class Md5DemoTest {

    @Test
    public void testEncode() {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update("A".getBytes());
            byte[] digest = md5.digest();
            System.out.println("md5=" + byte2str(digest));

            Md5Demo.MD5 md51 = new Md5Demo.MD5();
            System.out.println("md5=" + md51.getMD5("A"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 将字节数组转换成十六进制字符串
     */
    private static String byte2str(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte byte0 : bytes) {
            result.append(hex[byte0 >>> 4 & 0xf]);
            result.append(hex[byte0 & 0xf]);
        }
        return result.toString();
    }
}