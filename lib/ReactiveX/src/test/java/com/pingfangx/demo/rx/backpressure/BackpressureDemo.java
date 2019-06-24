package com.pingfangx.demo.rx.backpressure;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 示例与处理
 *
 * @author pingfangx
 * @date 2019/6/24
 */
public class BackpressureDemo {
    //介绍

    /**
     * 缺少背压
     */
    @Test
    public void test_MissingBackpressureException() throws InterruptedException {
        PublishProcessor<Integer> source = PublishProcessor.create();
        source
                .observeOn(Schedulers.computation())
                .subscribe(this::compute, Throwable::printStackTrace);
        for (int i = 0; i < 1_000_000; i++) {
            source.onNext(i);
        }
        sleep();
    }

    /**
     * 正常
     */
    @Test
    public void test_range() {
        Flowable.range(1, 1_000_000)
                .observeOn(Schedulers.computation())
                .subscribe(this::compute, Throwable::printStackTrace);
        sleep();
    }

    /**
     * 正常，onNext 中的调用解释 trampolining
     */
    @Test
    public void test_rangeReason() {
        Flowable.range(1, 1_000_000)
                .subscribe(new DisposableSubscriber<Integer>() {
                    @Override
                    protected void onStart() {
                        request(1);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        //这里实际并不会递归，而是使用 trampolining 逻辑，交换顺序不影响
                        //先记录有几次 request，onNext 结束再调用
                        request(1);
                        compute(integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Done");
                    }
                });
    }

    @Test
    public void test_onNextFromOnStart() {
        Flowable.range(1, 1_000_000)
                .subscribe(new DisposableSubscriber<Integer>() {
                    String name;

                    @Override
                    protected void onStart() {
                        request(1);
                        name = "name init from onStart";
                    }

                    @Override
                    public void onNext(Integer integer) {
                        //java.lang.NullPointerException,name 还未初始化
                        compute(name.length() + integer);
                        request(1);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    //操作符

    /**
     * 增加缓冲区，只能是临时修复
     */
    @Test
    public void test_increaseBufferSize() {
        PublishProcessor<Integer> source = PublishProcessor.create();
        source
                .observeOn(Schedulers.computation(), false, 1024 * 1024)
                .subscribe(this::compute, Throwable::printStackTrace);
        for (int i = 0; i < 1_000_000; i++) {
            source.onNext(i);
        }
        sleep();
    }

    /**
     * 批处理
     */
    @Test
    public void test_batch() {
        PublishProcessor<Integer> source = PublishProcessor.create();
        source
                .buffer(1024)
                .observeOn(Schedulers.computation(), false, 1024)
                .subscribe(list -> {
                    list.parallelStream().map(e -> e * e).findFirst();
                }, Throwable::printStackTrace);

        for (int i = 0; i < 1_000_000; i++) {
            source.onNext(i);
        }
        sleep();
    }

    /**
     * 采样
     */
    @Test
    public void test_sample() {
        PublishProcessor<Integer> source = PublishProcessor.create();
        source
                .sample(1, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.computation(), false, 1024)
                .subscribe(this::compute, Throwable::printStackTrace);

        for (int i = 0; i < 1_000_000; i++) {
            source.onNext(i);
        }
        sleep();
    }

    /**
     * 重新引入无界缓冲区
     */
    @Test
    public void test_onBackpressureBuffer() {
        Flowable.range(1, 1_000_000)
                .onBackpressureBuffer()
                .observeOn(Schedulers.computation(), false, 8)
                .subscribe(this::compute, Throwable::printStackTrace);
        sleep();
    }

    /**
     * 还是 MissingBackpressureException，但会说明 Buffer is full
     */
    @Test
    public void test_onBackpressureBufferWithCapacity() {
        Flowable.range(1, 1_000_000)
                .onBackpressureBuffer(16)
                .observeOn(Schedulers.computation())
                .subscribe(this::compute, Throwable::printStackTrace);
        sleep();
    }

    /**
     * 指定 Strategy
     * 还可以使用 onBackpressureDrop() 或 onBackpressureLatest()
     */
    @Test
    public void test_onBackpressureBufferWithStrategy() {
        Flowable.range(1, 1_000_000)
                .onBackpressureBuffer(16, () -> {
                }, BackpressureOverflowStrategy.DROP_OLDEST)
                .observeOn(Schedulers.computation())
                .subscribe(this::compute, Throwable::printStackTrace);
        sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void compute(Integer v) {
        System.out.println(v);
    }
}
