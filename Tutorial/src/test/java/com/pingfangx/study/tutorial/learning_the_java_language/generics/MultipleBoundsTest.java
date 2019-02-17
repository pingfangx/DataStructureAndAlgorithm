package com.pingfangx.study.tutorial.learning_the_java_language.generics;

/**
 * @author pingfangx
 * @date 2019/1/7
 */
public class MultipleBoundsTest {
    class A {
    }

    interface B {
    }

    interface C {
    }

    class D<T extends A & B & C> {
    }

    public void test() {
        class E extends A implements B, C {
        }
        D<E> d = new D<>();
    }

}
