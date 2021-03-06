package com.pingfangx.study.tutorial.basic.java.generics;

/**
 * @author pingfangx
 * @date 2019/9/27
 */
public class GenericMethodTest {

    /**
     * 泛型方法的语法包括类型参数列表，在尖括号内，它出现在方法的返回类型之前。
     * 即在 T 之前
     */
    public <T> T test(T t) {
        return t;
    }

    /**
     * 对于静态泛型方法，类型参数部分必须出现在方法的返回类型之前。
     * 还是 T 之前，没有别的位置可放，这两句话我还以为有别的含义
     */
    static public <T> T test2(T t) {
        return t;
    }

    public static <T> T test3(T t) {
        return t;
    }
}
