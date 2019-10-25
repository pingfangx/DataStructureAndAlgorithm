package com.pingfangx.study.book1.chapter14;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/19
 */
public class SuperClassGenericTest {
    @Test
    public void test() throws IllegalAccessException, InstantiationException {
        // int
        Class<Integer> intClass = int.class;
        Assert.assertNull(intClass.getSuperclass());
        Class<Integer> integerClass = Integer.class;
        //class java.lang.Integer
        Class<? super Integer> superclass = integerClass.getSuperclass();
        System.out.println(superclass.getName());
        //没有构造函数，如果有，也只能赋值给 Object
        //Object object = superclass.newInstance();
    }
}
