package com.pingfangx.study.tutorial.basic.essential.concurrency.executor;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pingfangx
 * @date 2019/10/24
 */
public class ExecutorTest {
    private static class DefaultThreadFactory implements ThreadFactory {
        /**
         * 线程数
         */
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        /**
         * 线程池数 static
         */
        private static final AtomicInteger pollNumber = new AtomicInteger(1);

        private final ThreadGroup threadGroup;
        private final String namePrefix;

        public DefaultThreadFactory() {
            threadGroup = Thread.currentThread().getThreadGroup();
            namePrefix = "线程池" + pollNumber.getAndIncrement() + ",线程";
        }

        @Override
        public Thread newThread(@NotNull Runnable r) {
            Thread t = new Thread(threadGroup, r, namePrefix + threadNumber.get(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    private static class MyRunnable implements Runnable {
        int id;

        public MyRunnable() {
        }

        public MyRunnable(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(id + "当前线程 " + Thread.currentThread());
        }
    }

    private static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            return null;
        }
    }

    @Test
    public void test_newThreadPool() {
        ExecutorService e1 = Executors.newCachedThreadPool();
        ExecutorService e2 = Executors.newCachedThreadPool(new DefaultThreadFactory());
        ExecutorService e3 = Executors.newFixedThreadPool(2);
        ExecutorService e4 = Executors.newFixedThreadPool(2, new DefaultThreadFactory());
        ExecutorService e5 = Executors.newSingleThreadExecutor();
        ExecutorService e6 = Executors.newSingleThreadExecutor(new DefaultThreadFactory());
        ScheduledExecutorService e7 = Executors.newScheduledThreadPool(2);
        ScheduledExecutorService e8 = Executors.newScheduledThreadPool(2, new DefaultThreadFactory());
        ScheduledExecutorService e9 = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService e10 = Executors.newSingleThreadScheduledExecutor(new DefaultThreadFactory());
        ExecutorService e11 = Executors.newWorkStealingPool();
        ExecutorService e12 = Executors.newWorkStealingPool(1);
    }

    @Test
    public void test_executeOrSubmit() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new MyRunnable());
        Future<?> future1 = executorService.submit(new MyRunnable());
        Future<String> future2 = executorService.submit(new MyRunnable(), "");
        Future<String> future3 = executorService.submit(new MyCallable());
    }

    @Test
    public void test_newCachedThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool(new DefaultThreadFactory());
        executorService.execute(new MyRunnable(1));
        executorService.execute(new MyRunnable(2));
        TimeUnit.MINUTES.sleep(1);
        executorService.shutdownNow();
    }

    @Test
    public void test_newFixedThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1, new DefaultThreadFactory());
        executorService.execute(new MyRunnable(1));
        executorService.execute(new MyRunnable(2));
        TimeUnit.MINUTES.sleep(1);
        executorService.shutdownNow();
    }


    public static void main(String[] args) {
    }
}
