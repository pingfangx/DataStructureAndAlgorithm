package com.pingfangx.algorithm.tool;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author pingfangx
 * @date 2019/2/17
 */
public class FlagsUtilsTest {
    /**
     * 参考 android.view.Window#addFlags
     */
    @Test
    public void testFlag() {
        for (int i = 0; i < 32; i++) {
            int value = 1 << i;
            System.out.println("左移" + i + "位，值为" + value);
            /*
            所以为什么相关 flags 是 0x1,0x2,0x4,0x8,0x10,0x20,0x40,0x80
            是因为二进制移位导致的
            可以理解为每一个十六进制位可以使用 1,2,4,8，对应二进制位的 4 个位
            要清除时 mask 这一位为 f 即可
             */
            System.out.println("0x" + addZero(Integer.toHexString(value), 8));
            System.out.println("0b" + Integer.toBinaryString(value));
        }
        FlagsUtils flagsUtils = new FlagsUtils();
        int flag = 1 | (1 << 1);
        flagsUtils.addFlags(flag);
        flagsUtils.clearFlags(1 << 1);
        assertTrue(flagsUtils.hasFlag(1));
        assertTrue(!flagsUtils.hasFlag(1 << 2));

        flagsUtils.addFlags(0x10);
        flagsUtils.addFlags(0x20);
        flagsUtils.addFlags(0x40);
        flagsUtils.addFlags(0x80);
        //设置后 mask 0xF0 即可清除
        flagsUtils.setFlags(0, 0xF0);
        //但是 0x01 仍然还在
        assertTrue(flagsUtils.hasFlag(1));
        assertTrue(!flagsUtils.hasFlag(0x10));
        assertTrue(!flagsUtils.hasFlag(0x20));
        assertTrue(!flagsUtils.hasFlag(0x40));
        assertTrue(!flagsUtils.hasFlag(0x80));
    }

    public static String addZero(String str, int length) {
        int add = length - str.length();
        if (add > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < add; i++) {
                sb.append('0');
            }
            sb.append(str);
            return sb.toString();
        } else {
            return str;
        }
    }
}