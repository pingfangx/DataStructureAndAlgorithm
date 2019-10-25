package com.pingfangx.study.book2.chapter02;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/24
 */
public class StackOverflowTest {
    int stackDepth;

    /**
     * java.lang.StackOverflowError
     * 大约 5000 多层，不固定
     * 无法捕获
     */
    @Test
    public void test_stackFrame() {
        System.out.println("stack " + (++stackDepth));
        try {
            test_stackFrame();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
