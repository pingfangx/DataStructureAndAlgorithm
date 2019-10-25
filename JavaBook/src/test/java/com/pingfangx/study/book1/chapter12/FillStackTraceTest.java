package com.pingfangx.study.book1.chapter12;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/18
 */
public class FillStackTraceTest {
    public int div() {
        return 1 / 0;
    }

    public void second() {
        div();
    }

    public void testThrow() throws Exception {
        try {
            second();
        } catch (Exception e) {
            e.printStackTrace();
            //重新填充，填充到当前方法
            e.fillInStackTrace();
            throw e;
        }
    }

    @Test
    public void test() {
        try {
            testThrow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
