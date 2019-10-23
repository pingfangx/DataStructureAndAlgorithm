package com.pingfangx.study.tutorial.basic.essential.concurrency.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author pingfangx
 * @date 2019/10/24
 */
public class ThreadLocalUsageTest {
    static class SessionUtils {
        int sessionId;

        public void openSession() {
            sessionId = ThreadLocalRandom.current().nextInt(100);
            System.out.println("open " + sessionId + ", " + Thread.currentThread());
        }

        public void closeSession() {
            System.out.println("close " + sessionId + ", " + Thread.currentThread());
        }
    }

    static class SingletonSessionUtils {
        private static final ThreadLocal<Integer> sessions = new ThreadLocal<>();
        private static final ThreadLocal<String> name = new ThreadLocal<>();

        public static void openSession() {
            sessions.set(ThreadLocalRandom.current().nextInt(100));
            name.set("name" + sessions.get());
            System.out.println("open " + sessions.get() + ", " + Thread.currentThread());
        }

        public static void closeSession() {
            System.out.println("close " + name.get() + ", " + sessions.get() + ", " + Thread.currentThread());
        }
    }

    /**
     * 每一个任务都有一个连接数
     */
    @Test
    public void test_threadLocal() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                SingletonSessionUtils.openSession();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SingletonSessionUtils.closeSession();
            });
        }
        executorService.shutdown();
    }

    @Test
    public void test_newRunnable() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                SessionUtils utils = new SessionUtils();
                utils.openSession();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                utils.closeSession();
            });
        }
        executorService.shutdown();
    }

    public static void main(String[] args) {
        new ThreadLocalUsageTest().test_threadLocal();
//        new ThreadLocalUsageTest().test_newRunnable();
    }
}
