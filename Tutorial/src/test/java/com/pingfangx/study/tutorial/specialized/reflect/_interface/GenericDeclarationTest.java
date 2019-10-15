package com.pingfangx.study.tutorial.specialized.reflect._interface;

import org.junit.Test;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/**
 * 类、构造函数、方法实现 GenericDeclaration
 *
 * @author pingfangx
 * @date 2019/10/15
 */
public class GenericDeclarationTest<K, V extends Number> {
    public <T> T method(T t, K k) {
        return t;
    }

    @Test
    public void test() {
        printClass(GenericDeclarationTest.class);
        GenericDeclarationTest<String, Integer> t = new GenericDeclarationTest<>();
        printClass(t.getClass());
    }

    private void printClass(GenericDeclaration genericDeclaration) {
        for (TypeVariable<?> typeParameter : genericDeclaration.getTypeParameters()) {
            System.out.printf("%s,%s,%s%n", typeParameter.getName(), typeParameter.getGenericDeclaration(), Arrays.toString(typeParameter.getBounds()));
        }
    }
}
