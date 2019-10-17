package com.pingfangx.study.book1.chapter21.section03;

/**
 * @author pingfangx
 * @date 2019/10/20
 */
public class SynchronizedObjectTest {
    private final Object syncObject = new Object();
    private static final int COUNT = 100;

    public synchronized void a() {
        for (int i = 0; i < COUNT; i++) {
            System.out.println("a()");
            Thread.yield();
        }
    }

    public synchronized void b() {
        for (int i = 0; i < COUNT; i++) {
            System.out.println("b()");
            Thread.yield();
        }
    }

    public void c() {
        synchronized (syncObject) {
            for (int i = 0; i < COUNT; i++) {
                System.out.println("c()");
                Thread.yield();
            }
        }
    }

    /**
     * 先执行 b 再执行 a 因为同步于同一个对象
     */
    static void test_synchronizedOnSameObject() {
        SynchronizedObjectTest test = new SynchronizedObjectTest();
        new Thread(test::a).start();
        test.b();
    }

    /**
     * a c 交替
     * 因为在不同对象上同步
     */
    static void test_synchronizedOnDiffObject() {
        SynchronizedObjectTest test = new SynchronizedObjectTest();
        new Thread(test::a).start();
        test.c();
    }

    public static void main(String[] args) {
//        test_synchronizedOnSameObject();
        test_synchronizedOnDiffObject();
    }
}
