package com.pingfangx.study.book1.chapter17;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/20
 */
public class StringHashCodeTest {
    @Test
    public void test() {
        String[] hellos = "Hello Hello".split(" ");
        System.out.println(hellos[0].hashCode());
        System.out.println(hellos[1].hashCode());
        System.out.println("Hello".hashCode());
        System.out.println(new String("Hello").hashCode());
        System.out.println(new String("Hello").hashCode());
        // 参象不相等，但是 hashCode 和 equals 是相等的
        System.out.println(new String("Hello") == new String("Hello"));
    }
}
