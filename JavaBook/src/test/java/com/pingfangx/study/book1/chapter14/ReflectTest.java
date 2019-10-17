package com.pingfangx.study.book1.chapter14;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author pingfangx
 * @date 2019/10/19
 */
public class ReflectTest {
    @Test
    public void test() {
        Method[] methods = String.class.getMethods();
        for (Method method : methods) {
            System.out.println(method.toString());
            System.out.println(method.toString().replaceAll("\\w+\\.", ""));
        }
    }
}
