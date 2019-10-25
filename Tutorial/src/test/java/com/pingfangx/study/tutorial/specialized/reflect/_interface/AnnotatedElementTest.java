package com.pingfangx.study.tutorial.specialized.reflect._interface;

import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.*;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 类、包、构造函数、方法字段等都是 AnnotatedElement
 *
 * @author pingfangx
 * @date 2019/10/15
 */
public class AnnotatedElementTest {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.METHOD, ElementType.CONSTRUCTOR})
    @Repeatable(RepeatableContainerTest.class)
    public @interface RepeatAbleTest {
        int count() default 1;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.METHOD, ElementType.CONSTRUCTOR})
    public @interface RepeatableContainerTest {
        RepeatAbleTest[] value();
    }

    @RepeatAbleTest(count = 2)
    @RepeatAbleTest(count = 3)
    @Test
    public void test() throws NoSuchMethodException {
        Class<AnnotatedElementTest> clazz = AnnotatedElementTest.class;
        AnnotatedElement testMethod = clazz.getMethod("test");
        Annotation[] annotations = testMethod.getAnnotations();
        System.out.println(Arrays.toString(annotations));

        //都可以获取
        System.out.println(Arrays.toString(testMethod.getAnnotationsByType(RepeatAbleTest.class)));
        System.out.println(Arrays.toString(testMethod.getAnnotationsByType(RepeatableContainerTest.class)));

        //实际获取的是容器类
        System.out.println(testMethod.getAnnotation(RepeatAbleTest.class));//null
        System.out.println(testMethod.getAnnotation(RepeatableContainerTest.class));

        //判断是否存在
        Assert.assertTrue(testMethod.isAnnotationPresent(Test.class));

        Method toStringMethod = clazz.getMethod("toString");
        //因为 @Retention(RetentionPolicy.SOURCE)
        Assert.assertFalse(toStringMethod.isAnnotationPresent(Override.class));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
