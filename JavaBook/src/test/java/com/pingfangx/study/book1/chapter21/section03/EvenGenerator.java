package com.pingfangx.study.book1.chapter21.section03;

/**
 * @author pingfangx
 * @date 2019/10/20
 */
class EvenGenerator extends IntGenerator {
    private int cur;

    @Override
    public int next() {
        cur++;
        Thread.yield();
        cur++;
        return cur;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator(), 10);
    }
}
