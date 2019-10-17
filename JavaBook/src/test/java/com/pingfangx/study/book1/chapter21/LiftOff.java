package com.pingfangx.study.book1.chapter21;

/**
 * @author pingfangx
 * @date 2019/10/20
 */
public class LiftOff implements Runnable {
    private static int taskCount;
    private final int id = taskCount++;

    protected int countDown = 10;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return String.format("#%d(%s),in thread %s", id, countDown > 0 ? countDown : "Liftoff!", Thread.currentThread());
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
            //Thread.yield();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        launch.run();
    }
}
