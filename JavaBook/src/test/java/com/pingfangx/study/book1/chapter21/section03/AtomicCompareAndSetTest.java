package com.pingfangx.study.book1.chapter21.section03;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pingfangx
 * @date 2019/10/21
 */
public class AtomicCompareAndSetTest extends IntGenerator {
    private AtomicInteger cur = new AtomicInteger();
    private int last;

    @Override
    public int next() {
        final int next = last + 2;
        /*
        只是在学习乐观锁的时候学习 compareAndSet 不知道这样使用对还是不对
         */
        if (cur.compareAndSet(last, next)) {
            //设置成功了才赋值
            last = next;
        }
        return last;
    }

    public static void main(String[] args) {
        EvenChecker.test(new AtomicCompareAndSetTest(), 10);
    }
}
