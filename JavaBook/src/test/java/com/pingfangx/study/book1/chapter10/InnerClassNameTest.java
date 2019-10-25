package com.pingfangx.study.book1.chapter10;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/18
 */
//重复
//class InnerClassNameTest$A {
//}

class InnerClassNameTest$1 {
}

class InnerClassNameTest$2 {
}

public class InnerClassNameTest {
    class A {
    }


    @Test
    public void test() {
        Object o = new Object() {
        };
        // 1、2 被占用，命名为 com.pingfangx.study.book1.chapter10.InnerClassNameTest$3
        System.out.println(o.getClass().getName());
    }
}
