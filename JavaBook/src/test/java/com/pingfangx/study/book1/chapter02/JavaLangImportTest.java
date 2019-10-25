package com.pingfangx.study.book1.chapter02;

import org.junit.Test;

import java.lang.ref.WeakReference;

/**
 * @author pingfangx
 * @date 2019/10/18
 */
public class JavaLangImportTest {
    @Test
    public void test() {
        // java.lang 不需要 import
        System.out.println(Math.abs(1));
        // java.lang 的子包需要 import
        System.out.println(new WeakReference<Integer>(2).get());
    }
}
