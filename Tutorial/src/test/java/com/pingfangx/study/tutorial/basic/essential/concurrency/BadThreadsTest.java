package com.pingfangx.study.tutorial.basic.essential.concurrency;

/**
 * @author pingfangx
 * @date 2019/10/21
 */
public class BadThreadsTest {
    static String message;

    static class CorrectorThread extends Thread {
        @Override
        public void run() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //1
            message = "Mares do eat oats.";
        }
    }

    /**
     * 这个示例是说
     * 该程序几乎总是打印出“Mares do eat oat”。但是，由于 "Key statement 1" 和 "Key statment 2" 之间没有happens-before 关系，因此无法保证此结果。即使 "Key statement 1" 实际上在 "Key statement 2" 之前执行，这也是如此。记住，happens-before 关系是关于可见性，而不是序列。
     */
    public static void main(String[] args) throws InterruptedException {
        new CorrectorThread().start();
        message = "Mares do not eat oats.";
        Thread.sleep(2000);
        //2
        System.out.println(message);
    }
}
