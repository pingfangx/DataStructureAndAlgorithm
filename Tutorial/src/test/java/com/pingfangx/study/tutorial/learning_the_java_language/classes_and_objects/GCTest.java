package com.pingfangx.study.tutorial.learning_the_java_language.classes_and_objects;

import org.junit.Test;

import java.lang.ref.WeakReference;

/**
 * @author pingfangx
 * @date 2018/7/5
 */
public class GCTest {
    class A {
    }


    @Test
    public void test() {
        //这里不太理解也不好测试
        A a = new A();
        WeakReference<Object> weakReference = new WeakReference<>(a);
        System.out.println(weakReference.get());
        a = null;
        System.gc();
        System.out.println(weakReference.get());
    }
}
