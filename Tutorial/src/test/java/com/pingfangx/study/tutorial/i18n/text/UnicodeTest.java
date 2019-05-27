package com.pingfangx.study.tutorial.i18n.text;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/5/29
 */
public class UnicodeTest {
    /**
     * Unicode 标准的有效代码点范围是 U+0000 到 U+10FFFF
     * code point (代码点) 是可以在编码字符集中使用的值。代码点是 32 位 int 数据类型，其中低 21 位表示有效代码点值，高 11 位表示 0。
     */
    @Test
    public void testCodePointRange() {
        int max = 0x10FFFF;
        String binaryString = Integer.toBinaryString(max);
        System.out.println(binaryString);
        System.out.println("length:" + binaryString.length());
    }

    /**
     * 测是代理对的范围
     * 为了支持补充字符而不更改 char 基本数据类型并导致与以前的 Java 程序不兼容，补充字符由一对称为 surrogates (代理) 的代码点值定义。
     * 第一个代码点是 high surrogates (高位代理) 范围 U+D800 到 U+DBFF，第二个代码点是 low surrogates (低位代理) 范围 U+DC00 到 U+DFFF。
     * 例如，Deseret 字符 LONG I，U+10400 由这对代理值定义：U+D801 和 U+DC00。
     * <p>
     * 根据 wiki https://zh.wikipedia.org/wiki/UTF-16
     * 码位减去0x10000,得到的值的范围为20比特长的0..0xFFFFF.
     * 高位的10比特的值（值的范围为0..0x3FF）被加上0xD800得到第一个码元或称作高位代理（high surrogate），值的范围是0xD800..0xDBFF.由于高位代理比低位代理的值要小，所以为了避免混淆使用，Unicode标准现在称高位代理为前导代理（lead surrogates）。
     * 低位的10比特的值（值的范围也是0..0x3FF）被加上0xDC00得到第二个码元或称作低位代理（low surrogate），现在值的范围是0xDC00..0xDFFF.由于低位代理比高位代理的值要大，所以为了避免混淆使用，Unicode标准现在称低位代理为后尾代理（trail surrogates）。
     */
    @Test
    public void testSurrogatesRage() {
        String c = "\uD801\uDC00";
        System.out.println(c);
        int codePointCount = c.codePointCount(0, c.length());
        //代码点数量为 1
        Assert.assertEquals(1, codePointCount);

        int codePoint = c.codePointAt(0);
        System.out.println(Integer.toHexString(codePoint));
        Assert.assertEquals(0x10400, codePoint);

        int remains = codePoint - 0x10000;
        System.out.println("remains:0b" + Integer.toBinaryString(remains));
        int high = (remains >> 10) + 0xD800;
        System.out.println("high:" + Integer.toHexString(high));

        int mask = (1 << 10) - 1;
        System.out.println("mask:0b" + Integer.toBinaryString(mask));
        int low = (remains & mask) + 0xDC00;
        System.out.println("low:" + Integer.toHexString(low));

        Assert.assertEquals("D801", Integer.toHexString(high).toUpperCase());
        Assert.assertEquals("DC00", Integer.toHexString(low).toUpperCase());
    }

    @Test
    public void testCharacterClassMethod() {
        Assert.assertEquals(2, Character.charCount(0x10400));
        String s = "\uD801\uDC00";
        Assert.assertEquals(1, s.codePointCount(0, s.length()));
        Assert.assertEquals(1, Character.charCount(1));
        Assert.assertTrue(Character.isJavaIdentifierStart('$'));
        Assert.assertFalse(Character.isJavaIdentifierStart('0'));
    }

    /**
     * 测试长度
     */
    @Test
    public void testStringLength() {
        String s = "\uD801\uDC00";
        Assert.assertEquals(2, s.length());
        Assert.assertEquals(2, s.toCharArray().length);
        Assert.assertEquals(1, s.codePointCount(0, s.length()));
    }
}
