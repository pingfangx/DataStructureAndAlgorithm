package com.pingfangx.study.book1.chapter15;

/**
 * @author pingfangx
 * @date 2019/10/19
 */
class Holder<T> {
    private T value;

    public Holder() {
    }

    public Holder(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}
