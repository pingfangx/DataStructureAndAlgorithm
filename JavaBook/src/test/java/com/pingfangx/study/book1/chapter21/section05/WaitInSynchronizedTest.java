package com.pingfangx.study.book1.chapter21.section05;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author pingfangx
 * @date 2019/10/20
 */
public class WaitInSynchronizedTest {
    /**
     * wait notify notifyAll 只能在同步方法或同步块里调用
     */
    private void waitInNonSynchronized() {
        try {
            wait();
        } catch (InterruptedException | IllegalMonitorStateException e) {
            //IllegalMonitorStateException
            e.printStackTrace();
        }
    }

    /**
     * wait this 的锁
     */
    private synchronized void waitInSynchronizedMethod() {
        System.out.println("开始等待" + Thread.currentThread());
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("被唤醒" + Thread.currentThread());
    }

    /**
     * this notify，唤醒成功
     */
    private synchronized void notifyAllInSynchronizedMethod() {
        System.out.println("调用 notify" + Thread.currentThread());
        notify();
        System.out.println("调用 notify 结束" + Thread.currentThread());
    }

    /**
     * this notify，唤醒成功，说明同步方法与 this 都是获取的对象的锁
     */
    private void notifyAllInSynchronizedThisBlock() {
        synchronized (this) {
            System.out.println("this 调用 notify" + Thread.currentThread());
            notify();
            System.out.println("this 调用 notify 结束" + Thread.currentThread());
        }
    }

    /**
     * 同步的 class 对象，notify 却是 this，所以异常
     */
    private void notifyAllInSynchronizedClassBlock() {
        synchronized (WaitInSynchronizedTest.class) {
            System.out.println("class 调用 notify" + Thread.currentThread());
            //IllegalMonitorStateException
            notify();
            System.out.println("class 调用 notify 结束" + Thread.currentThread());
        }
    }

    private static synchronized void waitInStaticSynchronizedMethod() {
        System.out.println("开始等待 class 的锁" + Thread.currentThread());
        try {
            //只能调用 class
            WaitInSynchronizedTest.class.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("被唤醒" + Thread.currentThread());
    }

    /**
     * 可以调用，但是只有等待 class 的锁的才会被唤醒
     */
    private void classNotifyAllInSynchronizedClassBlock() {
        synchronized (WaitInSynchronizedTest.class) {
            System.out.println("class 调用 notify" + Thread.currentThread());
            //IllegalMonitorStateException
            getClass().notify();
            System.out.println("class 调用 notify 结束" + Thread.currentThread());
        }
    }

    @Test
    public void test() throws InterruptedException {
        new Thread(WaitInSynchronizedTest::waitInStaticSynchronizedMethod).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("sleep 结束");
        new Thread(this::classNotifyAllInSynchronizedClassBlock).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("运行结束");
    }
}
