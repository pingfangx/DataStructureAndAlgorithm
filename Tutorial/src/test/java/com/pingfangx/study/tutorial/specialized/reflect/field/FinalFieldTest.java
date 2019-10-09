package com.pingfangx.study.tutorial.specialized.reflect.field;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author pingfangx
 * @date 2019/9/29
 */
public class FinalFieldTest {
    class A {
        private final int type = 1;
    }

    @Test
    public void test() {
        A a = new A();
        Assert.assertEquals(1, a.type);
        Class<A> clazz = A.class;
        try {
            Field field = clazz.getDeclaredField("type");
            Assert.assertFalse(field.isAccessible());
            field.setAccessible(true);
            Assert.assertTrue(field.isAccessible());
            field.setInt(a, 2);
            //修改失败，不相等
            Assert.assertNotEquals(2, a.type);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            Assert.assertFalse(modifiersField.isAccessible());
            modifiersField.setAccessible(true);
            Assert.assertTrue(modifiersField.isAccessible());
            Assert.assertTrue(Modifier.isFinal(field.getModifiers()));
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            Assert.assertFalse(Modifier.isFinal(field.getModifiers()));
            field.setInt(a, 2);
            //还是失败
            Assert.assertEquals(2, a.type);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
