package com.pingfangx.study.tutorial.basic.java.javaoo.initfields;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/9/27
 */
public class FinalMethodTest {
    private class AClass {

        protected int field = initField();

        protected int initField() {
            return 0;
        }
    }

    private class BClass extends AClass {
        @Override
        protected int initField() {
            return 1;
        }
    }

    @Test
    public void test() {
        //由方法初始化
        Assert.assertEquals(0, new AClass().field);
        //不设为 final 会被重写
        Assert.assertEquals(1, new BClass().field);
    }
}
