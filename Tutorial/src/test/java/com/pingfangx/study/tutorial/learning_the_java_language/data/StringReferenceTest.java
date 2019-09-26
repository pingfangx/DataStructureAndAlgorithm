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

    @Test
    public void test_reference() {
        String a = "1";
        String b = "1";
        //a b 引用的地址相同
        Assert.assertSame(a, b);
        //新建对象，地址不再相同
        Assert.assertNotSame(a, new String("1"));
        a = "2";
        //b 不变，它仍引用原来的地址，而 a 引用了新的地址
        Assert.assertEquals("1", b);

        b = a;
        a = "3";
        //一样，b = a 只是引用相同的地址，重新赋值 a 不影响 b 的地址
        Assert.assertEquals("2", b);
    }
}
