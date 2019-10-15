package com.pingfangx.study.tutorial.specialized.reflect._interface;

import org.junit.Test;

/**
 * 由 jvm 实现，Class 实现，不是数组则返回 null
 *
 * @author pingfangx
 * @date 2019/10/15
 */
public class GenericArrayTypeTest {
    @Test
    public void test() {
        System.out.println(Object.class.getComponentType());
        System.out.println(int[].class.getComponentType());
        System.out.println(Integer[].class.getComponentType());
        System.out.println(GenericArrayTypeTest[].class.getComponentType());
    }
}
