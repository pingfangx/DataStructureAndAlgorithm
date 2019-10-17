package com.pingfangx.study.book1.chapter14;

/**
 * @author pingfangx
 * @date 2019/10/19
 */
public class AbstractTest {
    class A extends Object {
        @Override
        public String toString() {
            return super.toString();
        }
    }

    abstract class B extends A {
        /**
         * 可以将已实现的方法再声明为 abstract
         */
        @Override
        abstract public String toString();
    }
}
