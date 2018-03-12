package com.pingfangx.study.map;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author pingfangx
 * @date 2018/3/9
 */
public class ComparableClassForTest {
    class Foo implements Comparable<Foo> {
        @Override
        public int compareTo(Foo o) {
            return 0;
        }
    }

    class Bar extends Foo {
    }

    class C extends Bar implements Comparable<Foo> {
    }

    @Test
    public void comparableClassForTest() {
        System.out.println(comparableClassFor1(new Foo()));
        System.out.println(comparableClassFor1(new Bar()));
        System.out.println(comparableClassFor2(new Foo()));
        System.out.println(comparableClassFor2(new Bar()));
        System.out.println(comparableClassFor2(new C()));
        // String 特殊处理，因为它是 final 没有子类，不会有下述问题
        System.out.println(comparableClassFor2(""));
    }

    static Class<?> comparableClassFor1(Object x) {
        if (x instanceof Comparable) {
            return x.getClass();
        }
        return null;
    }

    static Class<?> comparableClassFor2(Object x) {
        if (x instanceof Comparable) {
            Class<?> c = x.getClass();
            Type[] types = c.getGenericInterfaces();
            // Bar 中没有显示 implements ，返回空数组
            if (types != null) {
                for (Type type : types) {
                    if (type instanceof ParameterizedType) {
                        if (((ParameterizedType) type).getRawType() == Comparable.class) {
                            Type[] as = ((ParameterizedType) type).getActualTypeArguments();
                            if (as != null && as.length == 1) {
                                if (as[0] == c) {
                                    // C 中返回的是 Foo，不相等
                                    return c;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
