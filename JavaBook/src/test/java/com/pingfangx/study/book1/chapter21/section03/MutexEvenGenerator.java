package com.pingfangx.study.book1.chapter21.section03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pingfangx
 * @date 2019/10/20
 */
class MutexEvenGenerator extends IntGenerator {
    private int cur;
    private Lock lock = new ReentrantLock();

    @Override
    public int next() {
        lock.lock();
        try {
            cur++;
            Thread.yield();
            cur++;
            return cur;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator(), 10);
    }
}
