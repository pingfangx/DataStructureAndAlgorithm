package com.pingfangx.study.map;

import org.junit.Test;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pingfangx
 * @date 2018/3/14
 */
public class EnumMapTest {
    enum TestEnum {
        A, B, C {}, D {};
    }

    @Test
    public void testNotQuickFail() {
        Map<TestEnum, Object> map = new EnumMap<>(TestEnum.class);
        map.put(TestEnum.A, "a");
        map.put(TestEnum.B, "b");
        map.put(TestEnum.C, "c");
        map.put(TestEnum.D, null);
        MapUtils.printMap(map);

        System.out.println();
        //迭代中删除不会失败
        for (Map.Entry<TestEnum, Object> entry : map.entrySet()) {
            map.remove(TestEnum.B);
            MapUtils.printEntry(entry);
        }

        for (TestEnum testEnum : map.keySet()) {
            map.remove(TestEnum.D);
            System.out.println("testEnum = " + testEnum);
        }
    }

    class A {
    }

    class B extends A {
    }

    class C extends B {
    }

    @Test
    public void testIsValidKey() {
        Object[] objects = new Object[]{
                TestEnum.A,
                TestEnum.C,
                TestEnum.D,
                new A(),
                new B(),
                new C(),
        };

        for (Object o : objects) {
            System.out.println(String.format("class =%s,superClass=%s,declaringClass=%s,Enum.declaringClass=%s",
                    o.getClass(), o.getClass().getSuperclass(), o.getClass().getDeclaringClass(), o instanceof Enum ? ((Enum) o).getDeclaringClass() : null));
        }

    }
}
