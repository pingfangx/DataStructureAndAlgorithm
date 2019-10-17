package com.pingfangx.study.book1.chapter17;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/20
 */
public class CompareIntTest {
    /**
     * 比较 int 时不能直接减，要用 i1<i2
     */
    @Test
    public void test() {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        Integer i1 = -1 << 31;
        Integer i2 = (1 << 31) - 1;
        System.out.println(i1);
        System.out.println(i2);
        System.out.println("i1<i2 " + (i1 < i2));
        System.out.println("i1-i2 " + (i1 - i2));
    }
}
