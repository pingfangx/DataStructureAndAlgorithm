package com.pingfangx.study.tutorial.specialized.reflect._interface;

import org.junit.Test;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类型变量，即类型参数，类型形参，参见泛型术语
 * <p>
 * 通过 java.lang.Class#getTypeParameters() 获取
 *
 * @author pingfangx
 * @date 2019/10/15
 */
public class TypeVariableTest<K extends List<Number>, V> {
    @Test
    public void test() {
        printClass(TypeVariableTest.class);
        List<? super Integer> list = new ArrayList<>();
        printClass(list.getClass());
    }

    private void printClass(Class<?> clazz) {
        TypeVariable<? extends Class<?>>[] typeParameters = clazz.getTypeParameters();
        for (TypeVariable<? extends Class<?>> typeParameter : typeParameters) {
            Class<?> genericDeclaration = typeParameter.getGenericDeclaration();
            System.out.printf("name=%s,%s,%n", typeParameter.getName(), genericDeclaration);
            System.out.printf("bounds=%s,%n", Arrays.toString(typeParameter.getBounds()));
        }
    }
}
