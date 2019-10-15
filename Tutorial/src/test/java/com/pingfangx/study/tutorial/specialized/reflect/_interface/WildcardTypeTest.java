package com.pingfangx.study.tutorial.specialized.reflect._interface;

import org.junit.Test;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pingfangx
 * @date 2019/10/15
 */
public class WildcardTypeTest {

    private void unbounded(List<?> list) {
    }

    private void upper(List<? extends Number> list) {
    }

    private void lower(List<? super Integer> list) {
    }

    @Test
    public void test() throws NoSuchMethodException {
        printClass(WildcardTypeTest.class.getDeclaredMethod("unbounded", List.class));
        printClass(WildcardTypeTest.class.getDeclaredMethod("upper", List.class));
        printClass(WildcardTypeTest.class.getDeclaredMethod("lower", List.class));
        List<? extends Number> list = new ArrayList<>();
        TypeVariable<? extends Class<? extends List>>[] typeParameters = list.getClass().getTypeParameters();
        for (TypeVariable<? extends Class<? extends List>> typeParameter : typeParameters) {
            System.out.println(typeParameter.getName());
            Class<? extends List> genericDeclaration = typeParameter.getGenericDeclaration();
            Type genericSuperclass = genericDeclaration.getGenericSuperclass();
            System.out.println(genericSuperclass);
        }
    }

    private void printClass(Method method) {
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            if (genericParameterType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericParameterType;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    if (actualTypeArgument instanceof WildcardType) {
                        WildcardType wildcardType = (WildcardType) actualTypeArgument;
                        System.out.printf("name=%s,upper=%s,lower=%s%n", wildcardType.getTypeName(), Arrays.toString(wildcardType.getUpperBounds()), Arrays.toString(wildcardType.getLowerBounds()));
                    }
                }
            }
        }
    }
}
