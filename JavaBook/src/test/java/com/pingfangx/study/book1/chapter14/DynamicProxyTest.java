package com.pingfangx.study.book1.chapter14;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author pingfangx
 * @date 2019/10/19
 */
public class DynamicProxyTest {
    class DynamicProxyHandler implements InvocationHandler {
        private Object proxied;

        public DynamicProxyHandler(Object proxied) {
            this.proxied = proxied;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("动态创建的代理类为" + proxy.getClass());
            System.out.println("方法为" + method);
            System.out.println("参数为" + Arrays.toString(args));
            System.out.println("代理的对象为" + proxied);
            System.out.println("在代理的对象上执行方法");
            return method.invoke(proxied, args);
        }
    }

    interface Interface {
        void doSomething();
    }

    class RealObject implements Interface {

        @Override
        public void doSomething() {
            System.out.println("do something is real object.");
        }
    }

    @Test
    public void test() {
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),
                new Class[]{Interface.class},
                new DynamicProxyHandler(new RealObject()));
        proxy.doSomething();
    }
}
