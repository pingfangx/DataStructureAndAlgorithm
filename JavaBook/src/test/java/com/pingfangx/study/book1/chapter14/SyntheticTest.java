package com.pingfangx.study.book1.chapter14;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author pingfangx
 * @date 2019/10/19
 */
class SyntheticTest$1 {
}

public class SyntheticTest {
    class InnerClass {
        class InnerNestedClass {
        }
    }

    static class StaticInnerClass {
    }

    /**
     * 普通嵌套类都是使用 $ 连接
     * class com.pingfangx.study.book1.chapter14.SyntheticTest$InnerClass
     * class com.pingfangx.study.book1.chapter14.SyntheticTest$InnerClass$InnerNestedClass
     * class com.pingfangx.study.book1.chapter14.SyntheticTest$StaticInnerClass
     * //手动命名为 SyntheticTest$1
     * class com.pingfangx.study.book1.chapter14.SyntheticTest$1
     * //内部类以 $1 命名，会再加一个 $
     * class com.pingfangx.study.book1.chapter14.SyntheticTest$$1
     * //局部类和匿名类以数字命名，其中局部为为数字加名字，可能是为了与内部类区分
     * class com.pingfangx.study.book1.chapter14.SyntheticTest$1LocalClass
     * class com.pingfangx.study.book1.chapter14.SyntheticTest$2
     * class com.sun.proxy.$Proxy4
     */
    class $1 {
    }

    @Test
    public void test() {
        class LocalClass {
        }
        Object anonymousClass = new Object() {
        };

        Object proxy = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });

        System.out.println(InnerClass.class);
        System.out.println(InnerClass.InnerNestedClass.class);
        System.out.println(StaticInnerClass.class);
        System.out.println(SyntheticTest$1.class);
        System.out.println($1.class);
        System.out.println(LocalClass.class);
        System.out.println(anonymousClass.getClass());
        System.out.println(proxy.getClass());
    }
}
