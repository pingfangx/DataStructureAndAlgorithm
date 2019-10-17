package com.pingfangx.study.book1.chapter14;

import org.junit.Test;

import java.util.Random;

/**
 * @author pingfangx
 * @date 2019/10/19
 */
public class ClassAndInitialTest {
    static class A {
        static final int staticFinal = 1;
        static final double staticFinalRandom = Math.random();

        static {
            System.out.println("A initial");
        }
    }

    static class B {
        static int staticNonFinal = 2;
        static int staticNonFinalRandom = new Random().nextInt();

        static {
            System.out.println("B initial");
        }
    }

    static class C {
        static int staticNonFinal = 3;
        static int staticNonFinalRandom = new Random().nextInt();

        static {
            System.out.println("C initial");
        }
    }

    /**
     * class com.pingfangx.study.book1.chapter14.ClassAndInitialTest$A
     * after A.class //.class 不触发初始化
     * 1 //编译期常量不触发
     * A initial
     * 0.4179879999811591   // random 触发了
     * B initial
     * 2    //非编译期常量触发
     * 1900892389
     * C initial    //forName 触发
     * 3
     * 2142017906
     */
    @Test
    public void test() throws ClassNotFoundException {
        System.out.println(A.class);
        System.out.println("after A.class");
        System.out.println(A.staticFinal);
        System.out.println(A.staticFinalRandom);

        System.out.println(B.staticNonFinal);
        System.out.println(B.staticNonFinalRandom);

        Class.forName("com.pingfangx.study.book1.chapter14.ClassAndInitialTest$C");
        System.out.println(C.staticNonFinal);
        System.out.println(C.staticNonFinalRandom);
    }
}
