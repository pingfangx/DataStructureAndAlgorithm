package com.pingfangx.demo.rx.backpressure;

import io.reactivex.Flowable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subscribers.DisposableSubscriber;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.concurrent.Callable;

/**
 * 创建背压源
 *
 * @author pingfangx
 * @date 2019/6/24
 */
public class CreateBackpressureSourceDemo {
    @Test
    public void test_just() {
        Flowable.just(1)
                .subscribe(new DisposableSubscriber<Integer>() {
                    @Override
                    public void onStart() {
                        //实际会出错
                        request(0);
                    }

                    @Override
                    public void onNext(Integer v) {
                        System.out.println(v);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    int counter;

    int computeValue() {
        return ++counter;
    }

    /**
     * 错误用法，只会打印一次
     */
    @Test
    public void test_justWithMethod() {
        Flowable<Integer> o = Flowable.just(computeValue());

        o.subscribe(System.out::println);
        o.subscribe(System.out::println);
    }

    @Test
    public void test_justWithMethod2() {
        int temp = computeValue();
        Flowable<Integer> o = Flowable.just(temp);
        o.subscribe(System.out::println);
        o.subscribe(System.out::println);
    }

    /**
     * 正确用法，使用 fromCallable 会调用两次
     */
    @Test
    public void test_fromCallable() {
        Flowable<Integer> o = Flowable.fromCallable(this::computeValue);
        o.subscribe(System.out::println);
        o.subscribe(System.out::println);
    }

    @Test
    public void test_fromArray() {
        Flowable.fromArray(1, 2, 3, 4, 5)
                .subscribe(System.out::println);
    }

    @Test
    public void test_fromIterable() {
        Iterable<Integer> iterable = () -> new Iterator<Integer>() {
            int i;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                return ++i;
            }
        };

        Flowable.fromIterable(iterable)
                .take(5)
                .subscribe(System.out::println);
    }

    public void test_generate() throws FileNotFoundException {
        Flowable<Integer> o = Flowable.generate(
                (Callable<InputStream>) () -> new FileInputStream(""),
                (in, out) -> {
                    //out 类型为 Emitter
                },
                in -> {
                    try {
                        in.close();
                    } catch (IOException e) {
                        RxJavaPlugins.onError(e);
                    }
                }
        );
    }
}
