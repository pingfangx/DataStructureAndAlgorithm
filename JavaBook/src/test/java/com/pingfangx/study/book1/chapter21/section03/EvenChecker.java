package com.pingfangx.study.book1.chapter21.section03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pingfangx
 * @date 2019/10/20
 */
class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator generator, int id) {
        this.generator = generator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int next = generator.next();
            if (next > 1_000_0000) {
                generator.cancel();
                System.out.println("未产生竞争，请重试," + id + Thread.currentThread());
            }
            if (next % 2 != 0) {
                System.out.println("结果不是偶数 " + next + "," + id + Thread.currentThread());
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator generator, int n) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < n; i++) {
            executorService.execute(new EvenChecker(generator, i));
        }
        executorService.shutdownNow();
    }

}
