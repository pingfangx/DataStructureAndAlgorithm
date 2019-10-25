package com.pingfangx.study.book1.chapter06;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/18
 */
public class DefaultPackageTest {
    @Test
    public void test() throws ClassNotFoundException {
        Class clazz = Class.forName("DefaultPackageClass");
        System.out.println(clazz.getName());
        System.out.println(clazz.getPackage());
    }
}
