package com.pingfangx.study.book1.chapter21.section03;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pingfangx
 * @date 2019/10/20
 */
class AtomicEvenGenerator extends IntGenerator {
    private AtomicInteger cur = new AtomicInteger(0);

    @Override
    public int next() {
        return cur.addAndGet(2);
    }

    public static void main(String[] args) {
        EvenChecker.test(new AtomicEvenGenerator(), 10);
    }
}
