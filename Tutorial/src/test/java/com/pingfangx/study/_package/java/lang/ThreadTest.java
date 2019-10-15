package com.pingfangx.study._package.java.lang;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/14
 */
public class ThreadTest {
    @Test
    public void test_startAndRun() {
        Runnable runnable = () -> System.out.println("当前线程为 " + Thread.currentThread());
        Thread thread = new Thread(runnable);
        thread.run();//当前线程为 Thread[main,5,main]
        thread.start();//当前线程为 Thread[Thread-0,5,main]
        thread.run();//当前线程为 Thread[main,5,main]
        thread.start();//java.lang.IllegalThreadStateException
    }
}
