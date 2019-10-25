package com.pingfangx.study.book1.chapter10;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/18
 */
public class NewInnerClassTest {
    class Outer {
        class Inner {
        }
    }

    @Test
    public void test() {
        //Outer.Inner inner = new Outer.Inner();
        Outer.Inner inner = new Outer().new Inner();
        Outer outer = new Outer();
        inner = outer.new Inner();
    }
}
