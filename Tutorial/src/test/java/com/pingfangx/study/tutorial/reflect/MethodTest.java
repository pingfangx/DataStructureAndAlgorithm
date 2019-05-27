package com.pingfangx.study.tutorial.reflect;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author pingfangx
 * @date 2019/5/30
 */
public class MethodTest {
    public void varArgs(String... args) {
        System.out.println(args.getClass());
    }

    public void stringArray(String[] args) {
        System.out.println(args.getClass());
    }

    /**
     * 参数相同，但 toGenericString 和 isVarArgs 不相同
     */
    @Test
    public void test() {
        String[] args = new String[]{"1", "2"};
        varArgs("3", "4");
        varArgs(args);
        stringArray(args);
        //error
        //stringArray("3", "4");

        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.toGenericString());
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> type : parameterTypes) {
                System.out.println(type);
            }
            System.out.println("isVarArgs:" + method.isVarArgs());
        }
    }
}
