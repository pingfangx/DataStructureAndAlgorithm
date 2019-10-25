package com.pingfangx.study.tutorial.basic.essential.concurrency.sync;

/**
 * 线程干扰
 * <p>
 * 可能有时候能正常执行，多运行几次可以重现
 *
 * @author pingfangx
 * @date 2019/3/12
 */
public class ThreadInterfere {
    private void threadMessage(String msg, Object... args) {
        String name = Thread.currentThread().getName();
        System.out.println(name + ":" + String.format(msg, args));
    }


    private class Counter {
        private int c = 0;

        public void increment() {
            threadMessage("执行 +");
            int start = value();
            threadMessage("执行前 %s", start);
            c++;
            int end = value();
            threadMessage("执行后 %s", end);
            if (start + 1 != end) {
                threadMessage("+1 不正确，执行前 %d ，执行后 %d", start, end);
                System.exit(0);
            }
        }

        public void decrement() {
            threadMessage("执行 -");
            int start = value();
            threadMessage("执行前 %s", start);
            c--;
            int end = value();
            threadMessage("执行后 %s", end);
            if (start - 1 != end) {
                threadMessage("+1 不正确，执行前 %d ，执行后 %d", start, end);
                System.exit(0);
            }
        }

        public int value() {
            return c;
        }

    }

    public void run() throws InterruptedException {
        Counter counter = new Counter();
        long maxTimes = 1_000;
        long printTimes = maxTimes / 100;
        Thread t1 = new Thread(() -> {
            long times = 0;
            while (times++ < maxTimes) {
                counter.increment();
                if (times % printTimes == 0) {
                    threadMessage("执行 +1 中 %s 次", times);
                }
            }
            threadMessage("执行完毕");
        });
        Thread t2 = new Thread(() -> {
            long times = 0;
            while (times++ < maxTimes) {
                counter.decrement();
                if (times % printTimes == 0) {
                    threadMessage("执行 -1 中 %s 次", times);
                }
            }
            threadMessage("执行完毕");
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    public static void main(String[] args) throws InterruptedException {
        new ThreadInterfere().run();
    }
}
