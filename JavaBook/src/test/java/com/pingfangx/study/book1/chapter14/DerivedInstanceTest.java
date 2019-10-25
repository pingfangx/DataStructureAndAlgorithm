package com.pingfangx.study.book1.chapter14;

import org.junit.Test;

/**
 * @author pingfangx
 * @date 2019/10/19
 */
public class DerivedInstanceTest {
    class Base {
    }

    class Derived extends Base {
    }

    /**
     * 测试 Base 类型的 x
     * x instance of Base true
     * x instance of Derived false
     * Base.class.isInstance(x) true
     * Derived.class.isInstance(x) false    // instance 和 isInstance 结果相同
     * x.getClass() == Base.class true
     * x.getClass() == Derived.class false
     * x.getClass().equals(Base.class) true
     * x.getClass().equals(Derived.class) false     // == 和 equals 结果相同
     * Base.class.isAssignableFrom(x.getClass()) true
     * Derived.class.isAssignableFrom(x.getClass()) false
     * x.getClass().isAssignableFrom(Base.class) true
     * x.getClass().isAssignableFrom(Derived.class) true    // Base == Base，是 Derived 的超类
     * 测试 Derived 类型的 x
     * x instance of Base true
     * x instance of Derived true
     * Base.class.isInstance(x) true
     * Derived.class.isInstance(x) true
     * x.getClass() == Base.class false
     * x.getClass() == Derived.class true
     * x.getClass().equals(Base.class) false
     * x.getClass().equals(Derived.class) true
     * Base.class.isAssignableFrom(x.getClass()) true
     * Derived.class.isAssignableFrom(x.getClass()) true
     * x.getClass().isAssignableFrom(Base.class) false
     * x.getClass().isAssignableFrom(Derived.class) true
     */
    void test(Object x) {
        System.out.printf("测试 %s 类型的 x %n", x.getClass().getSimpleName());
        System.out.printf("x instance of Base %s %n", x instanceof Base);
        System.out.printf("x instance of Derived %s %n", x instanceof Derived);
        System.out.printf("Base.class.isInstance(x) %s %n", Base.class.isInstance(x));
        System.out.printf("Derived.class.isInstance(x) %s %n", Derived.class.isInstance(x));
        System.out.printf("x.getClass() == Base.class %s %n", x.getClass() == Base.class);
        System.out.printf("x.getClass() == Derived.class %s %n", x.getClass() == Derived.class);
        System.out.printf("x.getClass().equals(Base.class) %s %n", x.getClass().equals(Base.class));
        System.out.printf("x.getClass().equals(Derived.class) %s %n", x.getClass().equals(Derived.class));
        System.out.printf("Base.class.isAssignableFrom(x.getClass()) %s %n", Base.class.isAssignableFrom(x.getClass()));
        System.out.printf("Derived.class.isAssignableFrom(x.getClass()) %s %n", Derived.class.isAssignableFrom(x.getClass()));
        System.out.printf("x.getClass().isAssignableFrom(Base.class) %s %n", x.getClass().isAssignableFrom(Base.class));
        System.out.printf("x.getClass().isAssignableFrom(Derived.class) %s %n", x.getClass().isAssignableFrom(Derived.class));
    }

    @Test
    public void test() {
        test(new Base());
        test(new Derived());
    }
}
