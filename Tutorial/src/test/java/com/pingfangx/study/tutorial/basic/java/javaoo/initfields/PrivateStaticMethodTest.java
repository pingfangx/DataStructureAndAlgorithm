package com.pingfangx.study.tutorial.basic.java.javaoo.initfields;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/9/27
 */
public class PrivateStaticMethodTest {
    private static class TestClass {
        private static int field = initField();
        private static int count = 1;

        /**
         * 不是 private 也行
         */
        public static int initField() {
            return count;
        }

        public TestClass() {
            field = initField();
        }
    }

    @Test
    public void test() {
        //由方法初始化，但是要注意，因为 field 在 count 之前，所以初始化时 count 还没有赋值，所以 field 为 0
        Assert.assertEquals(TestClass.field, 0);
        //实际 count 是 1
        Assert.assertEquals(TestClass.count, 1);
        TestClass.count = 2;
        //相比于静态初始化块，静态方法可以多次调用
        Assert.assertEquals(new TestClass().field, 2);
    }
}
