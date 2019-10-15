package com.pingfangx.study.tutorial.specialized.reflect._interface;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 参数化类型，针对于泛型
 * 先要有泛型声明，然后才有参数化类型（可查阅泛型相关术语）
 * <p>
 * 关键就是如何获取 Type
 *
 * @author pingfangx
 * @date 2019/10/15
 */
public class ParameterizedTypeTest {
    private static class GenericDeclaration<K, V> {
    }

    private static class ParameterizeTypImplement extends GenericDeclaration<String, Integer> {
    }

    @Test
    public void test() {
        //不可以，getGenericSuperclass 是 Object
        System.out.println("GenericDeclaration\n");
        printClass(GenericDeclaration.class);

        System.out.println("\nParameterizeTypImplement\n");
        printClass(ParameterizeTypImplement.class);

        //可以，父类是 AbstractMap
        Map<Void, Enum> map = new HashMap<>();
        System.out.println("\nMap<Void, Enum>");
        printClass(map.getClass());
    }

    private void printClass(Class<?> clazz) {
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            System.out.println(parameterizedType.getRawType());
            System.out.println(parameterizedType.getOwnerType());
            System.out.println("getActualTypeArguments");
            for (Type actualTypeArgument : parameterizedType.getActualTypeArguments()) {
                System.out.println(actualTypeArgument.getTypeName());
            }
        }
    }
}
