package com.pingfangx.demo.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

/**
 * 订阅与观察示例
 *
 * @author pingfangx
 * @date 2019/6/28
 */
public class SubscribeOnAndObserveOnDemo {
    /**
     * 会依次调用，回到最先 subscribeOn 设置的线程
     * 而 onNext 会依次传递，直到最后的 observeOn 设置的线程
     */
    @Test
    public void testSubscribeOn() throws InterruptedException {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            System.out.println("subscribe on thread:" + Thread.currentThread().getName());
            emitter.onNext(1);
        })
                .subscribeOn(Schedulers.single())
                .subscribeOn(Schedulers.computation())
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.single())
                .observeOn(Schedulers.computation())
                .observeOn(Schedulers.newThread())
                .subscribe(integer -> System.out.println("observe on thread:" + Thread.currentThread().getName()));
        Thread.sleep(10 * 1000);
    }
}
