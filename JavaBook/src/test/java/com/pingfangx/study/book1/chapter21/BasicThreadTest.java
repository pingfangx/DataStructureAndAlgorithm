package com.pingfangx.study.book1.chapter21;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pingfangx
 * @date 2019/10/20
 */
public class BasicThreadTest {
    @Test
    public void test_basicThread() {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("等待起飞");
    }

    @Test
    public void test_moreThread() {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("等待起飞");
    }

    private void test_executor(ExecutorService executorService) {
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
        }
        executorService.shutdownNow();
    }

    @Test
    public void test_newCachedThreadPool() {
        test_executor(Executors.newCachedThreadPool());
    }

    @Test
    public void test_newFixedThreadPool() {
        test_executor(Executors.newFixedThreadPool(2));
    }

    @Test
    public void test_newSingleThreadExecutor() {
        test_executor(Executors.newSingleThreadExecutor());
    }

    public static void main(String[] args) {
        new BasicThreadTest().test_newFixedThreadPool();
    }
}
