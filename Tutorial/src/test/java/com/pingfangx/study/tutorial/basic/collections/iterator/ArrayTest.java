package com.pingfangx.study.tutorial.basic.collections.iterator;

import org.junit.Test;

import java.util.Iterator;

/**
 * @author pingfangx
 * @date 2019/10/25
 */
public class ArrayTest {
    @Test
    public void test() {
        Object[] array = {};
        Class<? extends Object[]> clazz = array.getClass();
        System.out.println("class=" + clazz);
        System.out.println(clazz.isArray());
        System.out.println(Iterable.class.isInstance(array));
        System.out.println(Iterator.class.isInstance(array));
    }
}
