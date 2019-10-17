package com.pingfangx.study.book1.chapter21;

import java.util.concurrent.TimeUnit;

/**
 * @author pingfangx
 * @date 2019/10/20
 */
public class DaemonTest {
    static class SimpleDaemons implements Runnable {
        @Override
        public void run() {
            try {
                int i = 0;
                while (i++ < 10) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println(Thread.currentThread() + " " + this);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("sleep() interrupted");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(200);
    }
}
