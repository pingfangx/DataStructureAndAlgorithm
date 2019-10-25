package com.pingfangx.study.book1.chapter15;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/19
 */
public class CaptureConversionTest {
    <T> void f1(Holder<T> holder) {
        T t = holder.get();
        System.out.println(t.getClass().getName());
    }

    void f2(Holder<?> holder) {
        f1(holder);
    }

    void f3(Holder holder) {
        f1(holder);
    }

    @Test
    public void test() {
        Holder raw = new Holder<Integer>();
        raw.set(1);
        f1(raw);
        f2(raw);
        f3(raw);

        Holder rawBasic = new Holder();
        rawBasic.set(new Object());
        f1(rawBasic);
        f2(rawBasic);
        f3(rawBasic);

        Holder<?> wildcard = new Holder<Double>(1D);
        f1(wildcard);
        f2(wildcard);
        f3(wildcard);
    }
}
