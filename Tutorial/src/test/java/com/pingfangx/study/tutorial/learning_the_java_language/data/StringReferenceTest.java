package com.pingfangx.study.tutorial.learning_the_java_language.data;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/6/4
 */
public class StringReferenceTest {
    @Test
    public void test() {
        String a = "abc";
        String b = "abc";
        //是同一对象
        Assert.assertSame(a, b);
        //不是
        String c = new String("abc");
        Assert.assertNotSame(a, c);
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));
        System.out.println(System.identityHashCode(c));
    }

    @Test
    public void testConcatTest() {
        String a = "a";
        String b = "b";
        String c = "c";
        //编译时优化
        String d = "a" + "b" + "c";
        //未优化，编译为 StringBuilder 连接
        String e = a + b + c;
        String abc = "abc";
        Assert.assertSame(abc, d);
        Assert.assertNotSame(abc, e);
    }
}
