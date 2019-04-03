package com.pingfangx.study.tutorial.essential_java_classes.concurrency;

/**
 * 简单的示例
 *
 * @author pingfangx
 * @date 2019/3/12
 */
public class SimpleThreads {
    static void threadMessage(String msg) {
        String name = Thread.currentThread().getName();
        System.out.format("%s:%s%n", name, msg);
    }

    private static class MessageLoop implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(1000);
                    threadMessage("running " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                threadMessage("interrupted.");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        threadMessage("start message loop");
        long start = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();
        threadMessage("waiting message loop");
        while (t.isAlive()) {
            t.join(1000);
            if (t.isAlive()) {
                if ((System.currentTimeMillis() - start) > 1000 * 10) {
                    threadMessage("has waited too long time, interrupt it");
                    t.interrupt();
                    // interrupt 后也需要加
                    t.join();
                    threadMessage("after interrupt");
                } else {
                    threadMessage("still waiting");
                }
            } else {
                threadMessage("exit because t is not alive");
            }
        }
        threadMessage("exit");
    }
}
