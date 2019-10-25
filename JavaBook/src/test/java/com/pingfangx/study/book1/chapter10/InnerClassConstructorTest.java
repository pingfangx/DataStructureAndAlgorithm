package com.pingfangx.study.book1.chapter10;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/18
 */
public class InnerClassConstructorTest {
    class A {
        /*
        public <init>(Lcom/pingfangx/study/book1/chapter10/InnerClassConstructorTest;Lcom/pingfangx/study/book1/chapter10/InnerClassConstructorTest;)V
        依然会添加外部类的参数，这是必须的，因为无法保证用户写的构造函数正确赋值 this$0 字段
         */
        public A(InnerClassConstructorTest outer) {
        }

        public A(InnerClassConstructorTest outer, int i) {
        }
    }

    @Test
    public void test() {
        System.out.println(A.class.getName());
    }
}
