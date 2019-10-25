package com.pingfangx.study.tutorial.basic.java.generics;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/1/8
 */
public class ParameterizedTypeStaticField {

    //泛型类不能继承 'java.lang.Throwable'
    //static class Animal<T> extends Throwable{
    static class Animal<T> {
        public static int count;
        //无法声明类型为类型参数的静态字段
        //private static T t;

        public Animal(T t) {
            count++;
        }
    }

    @Test
    public void test() {
        new Animal<>("");
        System.out.println(Animal.count);
    }
}
