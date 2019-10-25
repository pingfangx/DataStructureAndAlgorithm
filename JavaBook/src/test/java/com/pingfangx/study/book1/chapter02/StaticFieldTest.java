package com.pingfangx.study.book1.chapter02;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/17
 */
public class StaticFieldTest {
    static int si;
    static int sj = 1;
    int i;
    int j = 1;

    @Test
    public void test() {
        System.out.println(StaticFieldTest.si);
        System.out.println(StaticFieldTest.sj);
        System.out.println(++StaticFieldTest.si);
    }
}
