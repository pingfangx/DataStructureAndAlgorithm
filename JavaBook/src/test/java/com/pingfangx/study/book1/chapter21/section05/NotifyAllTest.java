package com.pingfangx.study.book1.chapter21.section05;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author pingfangx
 * @date 2019/10/20
 */
public class NotifyAllTest {
    static class Blocker {
        synchronized void waitingCall() {
            try {
                while (!Thread.interrupted()) {
                    wait();
                    System.out.println("被唤醒 " + Thread.currentThread());
                }
            } catch (InterruptedException e) {
                System.out.println("被中断 " + Thread.currentThread());
            }
        }

        synchronized void prod() {
            notify();
        }

        synchronized void prodAll() {
            notifyAll();
        }
    }

    static class Task1 implements Runnable {
        static Blocker blocker = new Blocker();

        @Override
        public void run() {
            blocker.waitingCall();
        }
    }

    static class Task2 implements Runnable {
        static Blocker blocker = new Blocker();

        @Override
        public void run() {
            blocker.waitingCall();
        }
    }

    /**
     * Blocker 是 static 的，所以共享一个
     * notifyAll 会唤醒所有的 Task1
     */
    @Test
    public void test() throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Task1());
        }
        exec.execute(new Task2());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            boolean prod = true;

            @Override
            public void run() {
                if (prod) {
                    System.out.println("notify");
                    Task1.blocker.prod();
                    prod = false;
                } else {
                    System.out.println("notifyAll");
                    Task1.blocker.prodAll();
                    prod = true;
                }
            }
        }, 400, 400);
        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        System.out.println("timer canceled");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("Task2.blocker.prodAll()");
        Task2.blocker.prodAll();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("shutdownNow");
        exec.shutdownNow();
    }
}
