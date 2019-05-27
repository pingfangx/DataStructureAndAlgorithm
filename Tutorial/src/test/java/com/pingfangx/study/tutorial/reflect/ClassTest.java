package com.pingfangx.study.tutorial.reflect;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author pingfangx
 * @date 2019/5/30
 */
public class ClassTest {
    enum E {A, B}

    @Test
    public void testClass() throws ClassNotFoundException {
        System.out.println("getClass()");
        printClass("foo".getClass());
        printClass(System.out.getClass());
        printClass(E.A.getClass());
        byte[] bytes = new byte[1024];
        printClass(bytes.getClass());
        Set<String> set = new HashSet<String>();
        printClass(set.getClass());
        Object o = new HashSet<String>();
        printClass(o.getClass());
        printClass(new byte[1024].getClass());

        System.out.println();
        System.out.println(".class");
        printClass(boolean.class);
        printClass(byte.class);
        printClass(short.class);
        printClass(int.class);
        printClass(long.class);
        printClass(char.class);
        printClass(float.class);
        printClass(double.class);
        printClass(void.class);
        printClass(int[][][].class);

        System.out.println();
        System.out.println("Class.forName()");
        printClass(Class.forName("[[Ljava.lang.String;"));

        System.out.println();
        System.out.println("TYPE");
        printClass(Void.TYPE);
    }

    private class A {
        public class APublic {
        }

        private class AInner {
        }
    }

    private class B extends A {
        public class BPublic {
        }

        private class BInner {
        }
    }

    /**
     * 测试返回类的方法
     */
    @Test
    public void testReturnClassMethod() {
        System.out.println("getSuperclass()");
        printClass(void.class.getSuperclass());
        printClass(String.class.getSuperclass());

        System.out.println("\ngetClasses()");
        printClass(Character.class.getClasses());

        //This includes public class and interface members inherited from superclasses and public class and interface members declared by the class.
        System.out.println("A.class.getClasses()");
        printClass(A.class.getClasses());
        System.out.println("B.class.getClasses()");
        printClass(B.class.getClasses());


        //This includes public, protected, default (package) access, and private classes and interfaces declared by the class, but excludes inherited classes and interfaces.
        System.out.println("\ngetDeclaredClasses()");
        printClass(Character.class.getDeclaredClasses());
        System.out.println("A.class.getDeclaredClasses()");
        printClass(A.class.getDeclaredClasses());
        System.out.println("B.class.getDeclaredClasses()");
        printClass(B.class.getDeclaredClasses());
    }

    static class StaticNestedClass {
    }

    class InnerClass {
        private void test() {
            class LocalClass {
            }
            Object anonymousObject = new Object() {
                public void m() {
                }
            };
        }
    }

    /**
     * <pre>
     *     根据 嵌套类 一节，分为
     *     类
     *          嵌套类
     *              静态嵌套类
     *              内部类
     *                 局部类
     *                 匿名类
     * </pre>
     */
    @Test
    public void testNestedClass() {
        printNestedClass(StaticNestedClass.class);
        printNestedClass(InnerClass.class);
        class LocalClass {
        }
        printNestedClass(LocalClass.class);
        Object o = new Object() {
        };
        printNestedClass(o.getClass());
    }

    private void printNestedClass(Class clazz) {
        System.out.println();
        System.out.println(clazz.getSimpleName());
        printClass(clazz);
        System.out.println("getDeclaringClass()");
        printClass(clazz.getDeclaringClass());
        System.out.println("getEnclosingClass()");
        printClass(clazz.getEnclosingClass());
    }

    private void printClass(Class[] classes) {
        for (Class clazz : classes) {
            printClass(clazz);
        }
    }

    private void printClass(Class clazz) {
        System.out.println(clazz);
    }
}
