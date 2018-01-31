package com.pingfangx.study;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2018/1/31
 */
public class InterfaceDefaultMethodTest {
    interface IA {
        void a();

        default void b() {
            System.out.println("IA b()");
        }
    }

    interface IB extends IA {
    }

    class A implements IA {
        @Override
        public void a() {
            System.out.println("A a()");
        }
    }

    class B implements IB {
        @Override
        public void a() {
            System.out.println("b a()");
        }

        @Override
        public void b() {
            System.out.println("b b()");
        }
    }

    @Test
    public void test() {
        new A().a();
        new A().b();
        new B().a();
        new B().b();
    }
}
