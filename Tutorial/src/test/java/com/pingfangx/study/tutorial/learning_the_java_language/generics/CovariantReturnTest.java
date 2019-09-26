package com.pingfangx.study.tutorial.learning_the_java_language.generics;

/**
 * @author pingfangx
 * @date 2019/9/27
 */
public class CovariantReturnTest {
    private class A1 {
        public class A {
            public A create() {
                return new A();
            }
        }

        public class B extends A {
            @Override
            public B create() {
                return new B();
            }
        }

        public class C extends B {
            //无法编译
//            @Override
//            public A create() {
//                return super.create();
//            }
        }
    }

    //猜测可能是想表达这样的情况
    private class A2 {

        public class A {
            public Object create() {
                return new Object();
            }
        }

        public class B extends A {
            @Override
            public Object create() {
                return super.create();
            }
        }

        public class C extends B {
            @Override
            public Object create() {
                return super.create();
            }
        }
    }

    //可以将 B 改为无界泛型
    private class A3 {

        public class A {
            public Object create() {
                return new Object();
            }
        }

        public class B<T> extends A {
            @Override
            public T create() {
                return null;
            }
        }

        public class C extends B {
            @Override
            public Object create() {
                return super.create();
            }
        }
    }

    //但是不能改成有界的
    private class A4 {

        public class A {
            public Object create() {
                return new Object();
            }
        }

        public class B<T extends Number> extends A {
            @Override
            public T create() {
                return null;
            }
        }

        public class C extends B {

            //无法编译
//            @Override
//            public Object create() {
//                return super.create();
//            }
        }
    }
}
