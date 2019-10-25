package com.pingfangx.study.book1.chapter05;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/18
 */
public class FinalizeTest {
    private class TestClass {
        private String name;
        private boolean mIsOpen;

        public TestClass(String name) {
            this.name = name;
        }

        public void open() {
            mIsOpen = true;
        }

        public void close() {
            mIsOpen = false;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            if (mIsOpen) {
                System.err.println(name + "还没有关闭");
            }
        }
    }

    @Test
    public void test() {
        TestClass t1 = new TestClass("1");
        t1.open();//没有置为 null 不会回收

        TestClass t2 = new TestClass("2");
        t2.open();
        t2 = null;//2还没有关闭

        TestClass t3 = new TestClass("3");
        t3.open();
        t3.close();
        t3 = null;//已经关闭

        new TestClass("4").open();//4还没有关闭，没有引用

        System.gc();
    }
}
