package com.pingfangx.study.tutorial.basic.java.javaoo;

import org.junit.Assert;
import org.junit.Test;

/**
 * 初始化字段测试
 *
 * @author pingfangx
 * @date 2018/7/5
 */
public class InitializingFieldsTest {
    /**
     * 实例变量
     */
    public class A {

        public A() {
            a = 1;
            //b 会初始化为2
        }

        public A(int b) {
            //会覆盖原先的 2
            this.b = b;
        }

        //初始化块
        {
            b = 2;
        }

        //位置不影响
        public int a;
        public int b;
        public int c = initWithFinalMethod();

        /**
         * 通过方法初始化，但这里是错误的，必须为 final
         * 否则会被子类重写
         */
        protected int initWithFinalMethod() {
            return 3;
        }
    }

    //不为 final 被子类重写
    public class B extends A {
        @Override
        protected int initWithFinalMethod() {
            return 4;
        }
    }
    /**
     * 类变量
     */
    public static class C {
        public static int a = 1;
        public static int b;

        //可以在任何地方使用 静态初始化块
        static {
            b = 22;
        }

        //可以有多个，按顺序
        static {
            b = 2;
        }

        public static int c = initWithStaticMethod();

        //可以使用 static 方法
        private static int initWithStaticMethod() {
            return 3;
        }
    }



    @Test
    public void test() {
        Assert.assertEquals(new A().a, 1);
        //初始化块
        Assert.assertEquals(new A().b, 2);
        Assert.assertEquals(new A(3).b, 3);
        Assert.assertEquals(new A().c, 3);
        Assert.assertEquals(new B().c, 4);

        Assert.assertEquals(C.a, 1);
        //静态初始化块
        Assert.assertEquals(C.b, 2);
        //static 方法
        Assert.assertEquals(C.c, 3);
    }
}
