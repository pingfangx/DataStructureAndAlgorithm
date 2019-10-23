package com.pingfangx.study.tutorial.basic.essential.concurrency.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 来自 https://blog.csdn.net/u011277123/article/details/92575771
 * 重现异常
 *
 * @author pingfangx
 * @date 2019/10/24
 */
public class ThreadLocalExceptionTest {
    /**
     * 原因是 SimpleDateFormat 不是线程安全的
     */
    private static class DateUtils {
        private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        public static Date parse(String date) {
            try {
                return sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 不知道这个测试用例是做什么的
     */
    private static class NumberUtils {
        public static int sum;

        public static int add10(int num) {
            sum = num;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return sum + 10;
        }
    }

    public void test_parse() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> System.out.println(DateUtils.parse("2019-10-24")));
        }
        executorService.shutdown();
    }

    public void test_add() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            int num = i;
            executorService.execute(() -> {
                System.out.println(num + " " + NumberUtils.add10(num));
            });
        }
        executorService.shutdown();
    }

    public static void main(String[] args) {
        new ThreadLocalExceptionTest().test_parse();
//        new ThreadLocalTest().test_add();
    }
}
