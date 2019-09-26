package com.pingfangx.study.tutorial.learning_the_java_language.classes_and_objects.init_fields;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/9/27
 */
public class InitializerBlockTest {
    private class TestClass {
        {
            field = 1;
        }

        private int field = 0;

        {
            field = 2;
        }

        public TestClass() {
        }

        public TestClass(int field) {
            this.field = field;
        }
    }

    @Test
    public void test() {
        //按顺序
        Assert.assertEquals(2, new TestClass().field);
        //插入到开头
        Assert.assertEquals(3, new TestClass(3).field);
    }
}
