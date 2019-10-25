package com.pingfangx.study.tutorial.basic.java.javaoo.initfields;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/9/27
 */
public class StaticInitializationBlockTest {
    private static class TestClass {
        static {
            field = 1;
        }

        private static int field = 0;

        public TestClass() {
            field = 3;
        }

        static {
            field = 2;
        }


        public TestClass(int field) {
            TestClass.field = field;
        }
    }

    @Test
    public void test() {
        //被最后一个静态初始化块赋值
        Assert.assertEquals(TestClass.field, 2);
        //补构造函数修改
        Assert.assertEquals(new TestClass().field, 3);
        Assert.assertEquals(new TestClass(4).field, 4);
    }
}
