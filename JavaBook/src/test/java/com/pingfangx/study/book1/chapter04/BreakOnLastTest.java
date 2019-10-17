package com.pingfangx.study.book1.chapter04;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/17
 */
public class BreakOnLastTest {
    @Test
    public void test() {
        int i = (int) (Math.random() * 10);
        switch (i) {
            default:
                System.out.println(i);//可能输出 31
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
        }
    }
}
