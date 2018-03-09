package com.pingfangx.study.tutorial.learning_the_java_language.language_basics;

/**
 * 无符号的 int 和 long 的测试
 *
 * @author pingfangx
 * @date 2018/5/18
 */
public class UnsignedNumberTest {
    /**
     * > In Java SE 8 and later, you can use the int data type to represent an unsigned 32-bit integer, which has a minimum value of 0 and a maximum value of 232-1.
     * <p>
     * 阅读了 [How to use the unsigned Integer in Java 8 and Java 9?](https://stackoverflow.com/questions/25556017)
     * 和 [Unsigned Integer Arithmetic API now in JDK 8](https://blogs.oracle.com/darcy/unsigned-integer-arithmetic-api-now-in-jdk-8)
     * <p>
     * 经过测试我们知道，int 最大值是 -2147483638~2147483647
     * 引入了无符号机制，但实际的存储没有发生变化，但是可以用相关的 unsigned 方法进行计算、输出。
     */
    public static void main(String[] args) {
        int a = Integer.MAX_VALUE;
        System.out.println("max is " + a);
        System.out.println("max+1 is " + (a + 1));

        int a_U = Integer.parseUnsignedInt("2147483648");
        System.out.println("a_U value is " + a_U);
        System.out.println("a_U unsignedString is " + Integer.toUnsignedString(a_U));
        System.out.println("a_U * 2 - 10 unsignedString is " + Integer.toUnsignedString(a_U * 2 - 10));
    }
}
