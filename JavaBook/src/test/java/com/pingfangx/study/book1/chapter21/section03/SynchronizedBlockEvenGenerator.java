package com.pingfangx.study.book1.chapter21.section03;

/**
 * @author pingfangx
 * @date 2019/10/20
 */
public class SynchronizedBlockEvenGenerator extends IntGenerator {
    private int cur;

    @Override
    public synchronized int next() {
        synchronized (this) {
            cur++;
            Thread.yield();
            cur++;
        }
        return cur;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedBlockEvenGenerator(), 10);
    }

}
