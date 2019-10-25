package com.pingfangx.study.book1.chapter21.section05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author pingfangx
 * @date 2019/10/20
 */
public class WaxTest {
    static class Car {
        private boolean waxOn;

        public synchronized void waxed() {
            waxOn = true;
            notifyAll();
        }

        public synchronized void buffed() {
            waxOn = false;
            notifyAll();
        }

        public synchronized void waitForWaxing() throws InterruptedException {
            while (!waxOn) {
                wait();
            }
        }

        public synchronized void waitForBuffing() throws InterruptedException {
            while (waxOn) {
                wait();
            }
        }
    }

    static class WaxOn implements Runnable {
        private Car car;

        public WaxOn(Car car) {
            this.car = car;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    System.out.println("上腊");
                    Thread.sleep(200);
                    car.waxed();
                    car.waitForBuffing();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("interrupt 退出");
            }
            System.out.println("退出上腊");
        }
    }

    static class WaxOff implements Runnable {
        private Car car;

        public WaxOff(Car car) {
            this.car = car;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    car.waitForWaxing();
                    System.out.println("抛光");
                    Thread.sleep(200);
                    car.buffed();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("interrupt 退出");
            }
            System.out.println("退出抛光");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new WaxOff(car));
        executorService.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
}
