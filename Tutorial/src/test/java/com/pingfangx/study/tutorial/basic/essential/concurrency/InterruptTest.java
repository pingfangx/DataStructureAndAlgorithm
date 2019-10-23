package com.pingfangx.study.tutorial.basic.essential.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author pingfangx
 * @date 2019/10/21
 */
public class InterruptTest {
    /**
     * 按照规范，任何通过抛出 InterruptedException 退出的方法都会在执行此操作时清除中断状态。
     * <p>
     * Thread.interrupted() 和 Thread.sleep() 处都可能发生中断
     */
    static class Task implements Runnable {
        @Override
        public void run() {
            int i = 0;
            try {
                while (i < 100) {
                    if (Thread.interrupted()) {
                        System.out.println("线和已中断");
                        throw new InterruptedException("手动抛出");
                    }
                    i++;
                    System.out.println("i=" + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("拦截到异常，当前 Thread.interrupted() =" + Thread.interrupted());
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Task());
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
        TimeUnit.SECONDS.sleep(1);
    }
}
