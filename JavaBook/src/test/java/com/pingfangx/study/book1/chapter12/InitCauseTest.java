package com.pingfangx.study.book1.chapter12;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/18
 */
public class InitCauseTest {
    class ExceptionTest extends RuntimeException {
        public ExceptionTest(String message) {
            super(message);
        }

        public ExceptionTest(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @Test
    public void test() {

        ExceptionTest e = new ExceptionTest("1");
        e.printStackTrace();
        e.initCause(new ArithmeticException("2"));
        e.printStackTrace();

        e = new ExceptionTest("错", new IllegalArgumentException("p"));
        e.printStackTrace();
        // Can't overwrite cause with
        e.initCause(new ArithmeticException("arithmetic"));
    }
}
