package com.pingfangx.study.book1.chapter15.selfbounding;

/**
 * @author pingfangx
 * @date 2019/10/19
 */
public class GenericsAndReturnType {
    interface GenericGetter<T extends GenericGetter<T>> {
        T get();
    }

    interface Getter extends GenericGetter<Getter> {
    }

    public void test(Getter g) {
        Getter getter = g.get();
        GenericGetter gg = g.get();
    }
}
